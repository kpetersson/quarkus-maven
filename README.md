# Quarkus-maven
A simple quarkus application with maven.

## Prerequisites

* [Maven 3.5.3+](https://maven.apache.org/install.html)
* [GraalVM rc12+](https://www.graalvm.org/)

## Development with hot reload
```
mvn compile quarkus:dev
```

#### Debug
```
mvn compile quarkus:dev -Ddebug=true
```  
And then attach a debugger to localhost:5005.

## Native
By default, the native executable is tailored for your operating system (Linux, macOS, Windows etc).
```
mvn package -Pnative
```  

Run the executable:  
```
./target/quarkus-maven-1.0.0-runner
```  

## Native in docker
This will instruct maven to produce an executable from docker:
```
mvn package -Pnative -Dnative-image.docker-build=true
```  
Then build the image with:
```
docker build -f src/main/docker/Dockerfile -t quarkus/quarkus-maven
```  
Then run the container using:
```
docker run -i --rm -p 8080:8080 quarkus/quarkus-maven
```  

## Usage
```
curl -i localhost:8080/nodelay
```
```
curl -i localhost:8080/delay
```
