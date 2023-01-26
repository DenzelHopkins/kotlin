# Part 1: Build the app using Maven
FROM maven:3.6.3-openjdk-17
COPY . /root/app
WORKDIR /root/app
RUN mvn clean package -Dmaven.test.skip
EXPOSE 8080
ENTRYPOINT ["java","-jar","/root/app/target/demo-0.0.1-SNAPSHOT.jar"]