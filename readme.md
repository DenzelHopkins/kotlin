# Kotlin project

## Compile project
> mvn clean package

## Run web service
>  java -jar target/demo-0.0.1-SNAPSHOT.jar

## Test REST

### Get all messages

> curl localhost:8090/messages

### Post one message

> curl --header "Content-Type: application/json" \
--request POST \
--data '{"name":"xyz","description":"xyz"}' \
http://localhost:8090/messages