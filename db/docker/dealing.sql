-- For learning JDBC & JPA
-- Created by Karol Hadala

CREATE TABLE "customers" (
  "id" SERIAL NOT NULL,
  "last_name" text,
  "first_name" text,
  "picture" bytea,
  Constraint "customers_pkey" Primary Key ("id")
);

CREATE TABLE "accounts" (
	"id" SERIAL NOT NULL,
	"name" text NOT NULL,
	"customer_id" integer,
	Constraint "accounts_id_pkey" Primary Key ("id")
);

CREATE TABLE "instruments" (
	"id" SERIAL NOT NULL,
	"name" text,
	Constraint "instruments_pkey" Primary Key ("id")
);

CREATE TABLE "deals" (
  "id" SERIAL NOT NULL,
  "account_id" integer NOT NULL,
  "instrument_id" integer NOT NULL,
  "open_timestamp" timestamp with time zone NOT NULL,
  "close_timestamp" timestamp with time zone NOT NULL,
  "open_price" numeric(5,2) NOT NULL,
  "close_price" numeric(5,2)
);

create or replace function bytea_import(p_path text, p_result out bytea)
                   language plpgsql as $$
declare
  l_oid oid;
  r record;
begin
  p_result := '';
  select lo_import(p_path) into l_oid;
  for r in ( select data
             from pg_largeobject
             where loid = l_oid
             order by pageno ) loop
    p_result = p_result || r.data;
  end loop;
  perform lo_unlink(l_oid);
end;$$;

COPY "customers"  FROM stdin;
1001	Williams	James	NULL
1002	Brown	Richard	NULL
1003	King	Jenny	NULL
1004	Bond	James	NULL
1005	Bond	Larry	NULL
\.

COPY "accounts"  FROM stdin;
150	SpreadBet	1001
91	SpreadBet	1002
113	CFD	1002
62	CFD	1003
105	SpreadBet	1003
99	StockBroking	1003
\.

COPY "instruments"  FROM stdin;
10	USD/EUR
11	EUR/USD
12	USD/GBP
13	GOLD
14	OIL
\.


COPY "deals"  FROM stdin;
10001	150	10	2016-08-01 09:29:21-07	2016-08-02 10:24:21-07	3.12	3.34
10002	150	12	2016-08-02 09:29:21-07	2016-08-03 10:24:21-07	4.12	3.44
10003	150	10	2016-08-04 09:29:21-07	2016-08-04 10:24:21-07	5.12	6.64
10004	91	10	2016-08-01 09:29:21-07	2016-08-01 10:24:21-07	6.12	7.35
10005	91	12	2016-08-02 09:29:21-07	2016-08-02 10:24:21-07	12.12	13.31
10006	91	11	2016-08-03 09:29:21-07	2016-08-03 10:24:21-07	11.12	9.84
10007	91	14	2016-08-04 09:29:21-07	2016-08-04 10:24:21-07	17.12	7.24
10008	113	14	2016-08-01 09:29:21-07	2016-08-01 10:24:21-07	18.12	9.30
10009	113	13	2016-08-02 09:29:21-07	2016-08-02 10:24:21-07	3.12	5.33
10010	113	14	2016-08-03 09:29:21-07	2016-08-03 10:24:21-07	4.12	13.31
10011	113	12	2016-08-04 09:29:21-07	2016-08-04 10:24:21-07	5.12	8.16
10012	62	10	2016-08-01 09:29:21-07	2016-08-01 10:24:21-07	6.12	7.38
10013	99	11	2016-08-01 09:29:21-07	2016-08-01 10:24:21-07	7.12	8.29
10014	99	12	2016-08-02 09:29:21-07	2016-08-02 10:24:21-07	8.12	9.38
10015	99	13	2016-08-03 09:29:21-07	2016-08-03 10:24:21-07	8.12	7.44
10016	99	14	2016-08-04 09:29:21-07	2016-08-04 10:24:21-07	9.12	9.38
10017	99	11	2016-08-05 09:29:21-07	2016-08-05 10:24:21-07	10.12	13.38
\.

update customers SET picture = bytea_import('/docker-entrypoint-initdb.d/picture.png') where id = 1001;
VACUUM FULL ANALYZE;
