#!/bin/bash

cd integration-test/http_server_spec
bundle install
bundle exec spinach --tags @0_file_server
