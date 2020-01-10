#!/bin/bash

USERNAME='postgres'
DATABASE='todolist'

function create_user {
  createuser ${USERNAME} -s
}

function create_database {
  createdb -O ${USERNAME} ${DATABASE}
}

if [[ $(psql postgres -tAc "SELECT 1 FROM pg_roles WHERE rolname='"${USERNAME}"'") != '1' ]]
then
  create_user
fi

if [[ $(psql postgres -tAc "SELECT 1 FROM pg_database WHERE datname='"${DATABASE}"'") != '1' ]]
then
  create_database
  $(psql -U postgres -d ${DATABASE} -tAc "CREATE TABLE TODO(ID SERIAL PRIMARY KEY NOT NULL, TITLE TEXT NOT NULL, COMPLETE BOOLEAN);")
fi
