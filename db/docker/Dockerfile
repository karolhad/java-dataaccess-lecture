FROM library/postgres
ENV POSTGRES_PASSWORD password1
ENV POSTGRES_DB dealing
ADD dealing.sql /docker-entrypoint-initdb.d/
