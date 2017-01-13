#!/bin/bash
docker run -it --rm --link bookstore-postgres:postgres postgres psql -h postgres -U postgres bookstore