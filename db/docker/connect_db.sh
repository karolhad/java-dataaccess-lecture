#!/bin/bash
docker run -it --rm --link dealing-postgres:postgres postgres psql -h postgres -U postgres dealing