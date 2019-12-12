# Java Todo List
[![Build Status](https://travis-ci.org/seoulection/webserver.svg?branch=req-res)](https://travis-ci.org/seoulection/webserver)

A todo list written in Java using web servers.
[Click here to view current deployment on Heroku](https://java-todo-list.herokuapp.com/)
## How to Run
Clone the repository to your local machine, then go inside the directory by running:
```
cd /webserver
```
Inside of the directory, run the following to start the server:
```
gradle run -Pport=<desired-port-number>
```
NOTE: If no port is specified, the port number will be defaulted to `5000`.
To exit the server, press `Ctrl-D`.
### Example
If you would like to create a server on port `8080`, then you would run:
```
gradle run -Pport=8080
```
This creates a server socket on port `8080`. Then inside of a browser, go to `localhost:8080` to see the "Hello World!" page.

## How to Run Tests
Inside of the root directory, run:
```
gradle test
```
Alternatively, you can run a test watcher by running:
```
gradle -t test
```
