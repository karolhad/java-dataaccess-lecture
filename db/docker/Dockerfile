FROM library/postgres
ENV POSTGRES_PASSWORD password1
ENV POSTGRES_DB bookstore
ADD bookstore.sql /docker-entrypoint-initdb.d/
