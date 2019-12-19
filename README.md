# Java Todo List
[![Build Status](https://travis-ci.org/seoulection/webserver.svg?branch=master)](https://travis-ci.org/seoulection/webserver)

A todo list written in Java using web servers.
[Click here to view current deployment on Heroku](https://java-todo-list.herokuapp.com/)
## How to Run
Clone the repository to your local machine, then go inside the directory by running:
```
cd /webserver
```
Inside of the directory, run the following to start the server:
```
gradle run -Dport=<desired-port-number> -Ddir=<desired-full-directory-to-serve>
```
Alternatively, you can execute the `.jar`. First, you must run:
```
gradle build
```
This will generate a `.jar` file inside of `/build/libs`. `cd` into this directory and run:
```
java -Dport=<desired-port-number> -Ddir=<desired-full-directory-to-serve> -jar webserver.jar
```
NOTE: If no port and directory are specified, the port number will be defaulted to `5000` and the default directory will be used.
To exit the server, press `Ctrl-D`.
### Example
If you would like to create a server on port `8080` and serve the directory `/Users/cloud/Desktop/directory-to-serve`, then you would run:
```
gradle run -Dport=8080 -Ddir=/Users/cloud/Desktop/directory-to-serve
```
Using the `.jar` file:
```
java -Dport=8080 -Ddir=/Users/cloud/Desktop/directory-to-serve -jar webserver.jar
```
This creates a server socket on port `8080` and serves the files in the specified directory.. Then inside of a browser, go to `localhost:8080` to see the contents.

## How to Run Tests
Inside of the root directory, run:
```
gradle test
```
Alternatively, you can run a test watcher by running:
```
gradle -t test
```
