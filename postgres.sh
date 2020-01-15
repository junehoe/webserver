#!/bin/bash

USERNAME='postgres'
DATABASE='todolist'

function create_user {
  createuser ${USERNAME} -s
}

function create_database {
  createdb -O ${USERNAME} ${DATABASE}
}

function create_table {
  psql -U $USERNAME -d $DATABASE -c "CREATE TABLE todo (ID SERIAL PRIMARY KEY NOT NULL, TITLE TEXT NOT NULL, COMPLETE BOOLEAN DEFAULT FALSE)"
}

function create_hardcoded_todos {
  psql -U $USERNAME -d $DATABASE -c "INSERT INTO todo (TITLE) VALUES ('Walk the dog')"
  psql -U $USERNAME -d $DATABASE -c "INSERT INTO todo (TITLE) VALUES ('Buy groceries from Whole Foods')"
  psql -U $USERNAME -d $DATABASE -c "INSERT INTO todo (TITLE) VALUES ('Implement PUT and DELETE')"
}

if [[ $(psql postgres -tAc "SELECT 1 FROM pg_roles WHERE rolname='"${USERNAME}"'") != '1' ]]
then
  create_user
fi

if [[ $(psql postgres -tAc "SELECT 1 FROM pg_database WHERE datname='"${DATABASE}"'") != '1' ]]
then
  create_database
  create_table
  create_hardcoded_todos
fi
