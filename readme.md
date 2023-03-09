# Kotlin project

<strong>Important</strong>: A running mongodb is needed.

## Native
### Compile project
> mvn clean package

### Run web service
>  java -jar target/demo-0.0.1-SNAPSHOT.jar

## Docker
### Build docker image
> docker build -t kotlin .

### Run docker container
> docker run -p 1000:1000 kotlin

### Login to docker registry
<strong>Important</strong>: Saved environment variable $CR_PAT is needed.

> echo $CR_PAT | docker login ghcr.io -u USERNAME --password-stdin

### Tag docker image
> docker tag kotlin ghcr.io/denzelhopkins/kotlin:latest

### Push docker image
> docker push ghcr.io/denzelhopkins/kotlin:latest

## Test REST

### Get all messages

> curl localhost:1000/messages

### Post one message

> curl --header "Content-Type: application/json" \
--request POST \
--data '{"name":"xyz","description":"xyz"}' \
http://localhost:1000/messages