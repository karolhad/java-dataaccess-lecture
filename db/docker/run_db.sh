#!/bin/bash
#docker run --name bookstore-postgres -e POSTGRES_PASSWORD=password1 -e POSTGRES_DB=bookstore -d postgres 
docker run --name bookstore-postgres -d bookstore_postgres 
