-- Modified version of http://www.commandprompt.com/ppbook/booktown.sql
-- For learning SQL on Heroku Postgres
-- Editted by Khurram Virani (@viranik) @lighthouse_labs

CREATE TABLE "books" (
	"id" integer NOT NULL,
	"title" text NOT NULL,
	"author_id" integer,
	"subject_id" integer,
	Constraint "books_id_pkey" Primary Key ("id")
);

--
-- TOC Entry ID 12 (OID 3117548)
--
-- Name: publishers Type: TABLE Owner: postgres
--

CREATE TABLE "publishers" (
	"id" integer NOT NULL,
	"name" text,
	"address" text,
	Constraint "publishers_pkey" Primary Key ("id")
);

--
-- TOC Entry ID 14 (OID 3389594)
--
-- Name: authors Type: TABLE Owner: manager
--

CREATE TABLE "authors" (
	"id" integer NOT NULL,
	"last_name" text,
	"first_name" text,
	Constraint "authors_pkey" Primary Key ("id")
);

--
-- TOC Entry ID 17 (OID 3390348)
--
-- Name: stock Type: TABLE Owner: postgres
--

CREATE TABLE "stock" (
	"isbn" text NOT NULL,
	"cost" numeric(5,2),
	"retail" numeric(5,2),
	"stock" integer,
	Constraint "stock_pkey" Primary Key ("isbn")
);

--
-- TOC Entry ID 4 (OID 3390416)
--
-- Name: subject_ids Type: SEQUENCE Owner: postgres
--

CREATE SEQUENCE "subject_ids" start 0 increment 1 maxvalue 2147483647 minvalue 0  cache 1 ;

--
-- TOC Entry ID 22 (OID 3391184)
--
-- Name: shipments Type: TABLE Owner: postgres
--

CREATE TABLE "shipments" (
	"id" integer DEFAULT nextval('"shipments_ship_id_seq"'::text) NOT NULL,
	"customer_id" integer,
	"isbn" text,
	"ship_date" timestamp with time zone
);

--
-- TOC Entry ID 24 (OID 3391454)
--
-- Name: customers Type: TABLE Owner: manager
--

CREATE TABLE "customers" (
	"id" integer NOT NULL,
	"last_name" text,
	"first_name" text,
	Constraint "customers_pkey" Primary Key ("id")
);

--
-- TOC Entry ID 6 (OID 3574018)
--
-- Name: book_ids Type: SEQUENCE Owner: postgres
--

CREATE SEQUENCE "book_ids" start 0 increment 1 maxvalue 2147483647 minvalue 0  cache 1 ;

--
-- TOC Entry ID 8 (OID 3628626)
--
-- Name: shipments_ship_id_seq Type: SEQUENCE Owner: manager
--

CREATE SEQUENCE "shipments_ship_id_seq" start 0 increment 1 maxvalue 2147483647 minvalue 0  cache 1 ;

--
-- TOC Entry ID 31 (OID 3628899)
--
-- Name: employees Type: TABLE Owner: postgres
--

CREATE TABLE "employees" (
	"id" integer NOT NULL,
	"last_name" text NOT NULL,
	"first_name" text,
	CONSTRAINT "employees_id" CHECK ((id > 100)),
	Constraint "employees_pkey" Primary Key ("id")
);

--
-- TOC Entry ID 32 (OID 3629174)
--
-- Name: editions Type: TABLE Owner: manager
--

CREATE TABLE "editions" (
	"isbn" text NOT NULL,
	"book_id" integer,
	"edition" integer,
	"publisher_id" integer,
	"publication" date,
	"type" character(1),
	CONSTRAINT "integrity" CHECK (((book_id NOTNULL) AND (edition NOTNULL))),
	Constraint "pkey" Primary Key ("isbn")
);

--
-- TOC Entry ID 10 (OID 3629402)
--
-- Name: author_ids Type: SEQUENCE Owner: manager
--

CREATE SEQUENCE "author_ids" start 0 increment 1 maxvalue 2147483647 minvalue 0  cache 1 ;


--
-- TOC Entry ID 38 (OID 3751882)
--
-- Name: subjects Type: TABLE Owner: postgres
--

CREATE TABLE "subjects" (
	"id" integer NOT NULL,
	"subject" text,
	"location" text,
	Constraint "subjects_pkey" Primary Key ("id")
);

--
-- TOC Entry ID 108 (OID 3751924)
--
-- Name: sum(text) Type: AGGREGATE Owner: postgres
--

CREATE AGGREGATE sum ( BASETYPE = text, SFUNC = textcat, STYPE = text, INITCOND = '' );


--
-- Data for TOC Entry ID 112 (OID 3117548)
--
-- Name: publishers Type: TABLE DATA Owner: postgres
--

COPY "publishers"  FROM stdin;
150	Kids Can Press	Kids Can Press, 29 Birch Ave. Toronto, ON M4V 1E2
91	Henry Holt & Company, Inc.	Henry Holt & Company, Inc. 115 West 18th Street New York, NY 10011
113	OReilly and Associates	OReilly and Associates, Inc. 101 Morris St, Sebastopol, CA 95472
62	Watson-Guptill Publications	1515 Boradway, New York, NY 10036
105	Noonday Press	Farrar Straus & Giroux Inc, 19 Union Square W, New York, NY 10003
99	Ace Books	The Berkley Publishing Group, Penguin Putnam Inc, 375 Hudson St, New York, NY 10014
101	Roc	Penguin Putnam Inc, 375 Hudson St, New York, NY 10014
163	Mojo Press	Mojo Press, PO Box 1215, Dripping Springs, TX 78720
171	Books of Wonder	Books of Wonder, 16 W. 18th St. New York, NY, 10011
102	Penguin	Penguin Putnam Inc, 375 Hudson St, New York, NY 10014
75	Doubleday	Random House, Inc, 1540 Broadway, New York, NY 10036
65	HarperCollins	HarperCollins Publishers, 10 E 53rd St, New York, NY 10022
59	Random House	Random House, Inc, 1540 Broadway, New York, NY 10036
\.

--
-- Data for TOC Entry ID 113 (OID 3389594)
--
-- Name: authors Type: TABLE DATA Owner: manager
--


COPY "authors"  FROM stdin;
1111	Denham	Ariel
1212	Worsley	John
15990	Bourgeois	Paulette
25041	Bianco	Margery Williams
16	Alcott	Louisa May
4156	King	Stephen
1866	Herbert	Frank
1644	Hogarth	Burne
2031	Brown	Margaret Wise
115	Poe	Edgar Allen
7805	Lutz	Mark
7806	Christiansen	Tom
1533	Brautigan	Richard
1717	Brite	Poppy Z.
2112	Gorey	Edward
2001	Clarke	Arthur C.
1213	Brookins	Andrew
\.


--
-- Data for TOC Entry ID 116 (OID 3390348)
--
-- Name: stock Type: TABLE DATA Owner: postgres
--


COPY "stock"  FROM stdin;
0385121679	29.00	36.95	65
039480001X	30.00	32.95	31
0394900014	23.00	23.95	0
044100590X	36.00	45.95	89
0441172717	17.00	21.95	77
0451160916	24.00	28.95	22
0451198492	36.00	46.95	0
0451457994	17.00	22.95	0
0590445065	23.00	23.95	10
0679803335	20.00	24.95	18
0694003611	25.00	28.95	50
0760720002	18.00	23.95	28
0823015505	26.00	28.95	16
0929605942	19.00	21.95	25
1885418035	23.00	24.95	77
0394800753	16.00	16.95	4
\.


--
-- Data for TOC Entry ID 120 (OID 3391184)
--
-- Name: shipments Type: TABLE DATA Owner: postgres
--


COPY "shipments"  FROM stdin;
375	142	039480001X	2001-08-06 09:29:21-07
323	671	0451160916	2001-08-14 10:36:41-07
998	1045	0590445065	2001-08-12 12:09:47-07
749	172	0694003611	2001-08-11 10:52:34-07
662	655	0679803335	2001-08-09 07:30:07-07
806	1125	0760720002	2001-08-05 09:34:04-07
102	146	0394900014	2001-08-11 13:34:08-07
813	112	0385121679	2001-08-08 09:53:46-07
652	724	1885418035	2001-08-14 13:41:39-07
599	430	0929605942	2001-08-10 08:29:42-07
969	488	0441172717	2001-08-14 08:42:58-07
433	898	044100590X	2001-08-12 08:46:35-07
660	409	0451457994	2001-08-07 11:56:42-07
310	738	0451198492	2001-08-15 14:02:01-07
510	860	0823015505	2001-08-14 07:33:47-07
997	185	039480001X	2001-08-10 13:47:52-07
999	221	0451160916	2001-08-14 13:45:51-07
56	880	0590445065	2001-08-14 13:49:00-07
72	574	0694003611	2001-08-06 07:49:44-07
146	270	039480001X	2001-08-13 09:42:10-07
981	652	0451160916	2001-08-08 08:36:44-07
95	480	0590445065	2001-08-10 07:29:52-07
593	476	0694003611	2001-08-15 11:57:40-07
977	853	0679803335	2001-08-09 09:30:46-07
117	185	0760720002	2001-08-07 13:00:48-07
406	1123	0394900014	2001-08-13 09:47:04-07
340	1149	0385121679	2001-08-12 13:39:22-07
871	388	1885418035	2001-08-07 11:31:57-07
1000	221	039480001X	2001-09-14 16:46:32-07
1001	107	039480001X	2001-09-14 17:42:22-07
754	107	0394800753	2001-08-11 09:55:05-07
458	107	0394800753	2001-08-07 10:58:36-07
189	107	0394800753	2001-08-06 11:46:36-07
720	107	0394800753	2001-08-08 10:46:13-07
1002	107	0394800753	2001-09-22 11:23:28-07
2	107	0394800753	2001-09-22 20:58:56-07
\.
--
-- Data for TOC Entry ID 121 (OID 3391454)
--
-- Name: customers Type: TABLE DATA Owner: manager
--


COPY "customers"  FROM stdin;
107	Jackson	Annie
112	Gould	Ed
142	Allen	Chad
146	Williams	James
172	Brown	Richard
185	Morrill	Eric
221	King	Jenny
270	Bollman	Julie
388	Morrill	Royce
409	Holloway	Christine
430	Black	Jean
476	Clark	James
480	Thomas	Rich
488	Young	Trevor
574	Bennett	Laura
652	Anderson	Jonathan
655	Olson	Dave
671	Brown	Chuck
723	Eisele	Don
724	Holloway	Adam
738	Gould	Shirley
830	Robertson	Royce
853	Black	Wendy
860	Owens	Tim
880	Robinson	Tammy
898	Gerdes	Kate
964	Gould	Ramon
1045	Owens	Jean
1125	Bollman	Owen
1149	Becker	Owen
1123	Corner	Kathy
\.


--
-- Data for TOC Entry ID 125 (OID 3628899)
--
-- Name: employees Type: TABLE DATA Owner: postgres
--


COPY "employees"  FROM stdin;
101	Appel	Vincent
102	Holloway	Michael
105	Connoly	Sarah
104	Noble	Ben
103	Joble	David
106	Hall	Timothy
1008	Williams	\N
\.
--
-- Data for TOC Entry ID 126 (OID 3629174)
--
-- Name: editions Type: TABLE DATA Owner: manager
--


COPY "editions"  FROM stdin;
039480001X	1608	1	59	1957-03-01	h
0451160916	7808	1	75	1981-08-01	p
0394800753	1590	1	59	1949-03-01	p
0590445065	25908	1	150	1987-03-01	p
0694003611	1501	1	65	1947-03-04	p
0679803335	1234	1	102	1922-01-01	p
0760720002	190	1	91	1868-01-01	p
0394900014	1608	1	59	1957-01-01	p
0385121679	7808	2	75	1993-10-01	h
1885418035	156	1	163	1995-03-28	p
0929605942	156	2	171	1998-12-01	p
0441172717	4513	2	99	1998-09-01	p
044100590X	4513	3	99	1999-10-01	h
0451457994	4267	3	101	2000-09-12	p
0451198492	4267	3	101	1999-10-01	h
0823015505	2038	1	62	1958-01-01	p
0596000855	41473	2	113	2001-03-01	p
\.
--
-- Data for TOC Entry ID 127 (OID 3629264)
--
-- Name: books Type: TABLE DATA Owner: manager
--


COPY "books"  FROM stdin;
7808	The Shining	4156	9
4513	Dune	1866	15
4267	2001: A Space Odyssey	2001	15
1608	The Cat in the Hat	1809	2
1590	Bartholomew and the Oobleck	1809	2
25908	Franklin in the Dark	15990	2
1501	Goodnight Moon	2031	2
190	Little Women	16	6
1234	The Velveteen Rabbit	25041	3
2038	Dynamic Anatomy	1644	0
156	The Tell-Tale Heart	115	9
41473	Programming Python	7805	4
41477	Learning Python	7805	4
41478	Perl Cookbook	7806	4
41472	Practical PostgreSQL	1212	4
\.

--
-- Data for TOC Entry ID 131 (OID 3751882)
--
-- Name: subjects Type: TABLE DATA Owner: postgres
--


COPY "subjects"  FROM stdin;
0	Arts	Creativity St
1	Business	Productivity Ave
2	Children's Books	Kids Ct
3	Classics	Academic Rd
4	Computers	Productivity Ave
5	Cooking	Creativity St
6	Drama	Main St
7	Entertainment	Main St
8	History	Academic Rd
9	Horror	Black Raven Dr
10	Mystery	Black Raven Dr
11	Poetry	Sunset Dr
12	Religion	\N
13	Romance	Main St
14	Science	Productivity Ave
15	Science Fiction	Main St
\.


--
-- TOC Entry ID 45 (OID 3117548)
--
-- Name: "unique_publisher_idx" Type: INDEX Owner: postgres
--

CREATE UNIQUE INDEX "unique_publisher_idx" on "publishers" using btree ( "name" "text_ops" );

--
-- TOC Entry ID 43 (OID 3391184)
--
-- Name: "shipments_ship_id_key" Type: INDEX Owner: postgres
--

CREATE UNIQUE INDEX "shipments_ship_id_key" on "shipments" using btree ( "id" "int4_ops" );

--
-- TOC Entry ID 44 (OID 3629264)
--
-- Name: "books_title_idx" Type: INDEX Owner: manager
--

CREATE  INDEX "books_title_idx" on "books" using btree ( "title" "text_ops" );

--
-- TOC Entry ID 140 (OID 3752079)
--
-- Name: sync_stock_with_editions Type: RULE Owner: manager
--

CREATE RULE sync_stock_with_editions AS ON UPDATE TO editions DO UPDATE stock SET isbn = new.isbn WHERE (stock.isbn = old.isbn);
--
-- TOC Entry ID 5 (OID 3390416)
--
-- Name: subject_ids Type: SEQUENCE SET Owner:
--

SELECT setval ('"subject_ids"', 15, 't');

--
-- TOC Entry ID 7 (OID 3574018)
--
-- Name: book_ids Type: SEQUENCE SET Owner:
--

SELECT setval ('"book_ids"', 41478, 't');

--
-- TOC Entry ID 9 (OID 3628626)
--
-- Name: shipments_ship_id_seq Type: SEQUENCE SET Owner:
--

SELECT setval ('"shipments_ship_id_seq"', 1011, 't');

--
-- TOC Entry ID 11 (OID 3629402)
--
-- Name: author_ids Type: SEQUENCE SET Owner:
--

SELECT setval ('"author_ids"', 25044, 't');

VACUUM FULL ANALYZE;
