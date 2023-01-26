# Kotlin project

<strong>Important</strong>: A running mongodb is needed.

## Native
### Compile project
> mvn clean package

### Run web service
>  java -jar target/demo-0.0.1-SNAPSHOT.jar

## Docker
### Build docker image
> docker build -t kotlin_web_api .

### Run docker container
>docker run -p 8080:8080 kotlin_web_api

## Test REST

### Get all messages

> curl localhost:8090/messages

### Post one message

> curl --header "Content-Type: application/json" \
--request POST \
--data '{"name":"xyz","description":"xyz"}' \
http://localhost:8090/messages