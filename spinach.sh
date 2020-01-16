#!/bin/bash

cd http_server_spec
bundle install
bundle exec spinach --tags @0_file_server
bundle exec spinach --tags @1_forms
bundle exec spinach --tags @2_idempotent_unsafe
