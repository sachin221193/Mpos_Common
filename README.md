# common
This application was generated using JHipster 5.0.2, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v5.0.2](https://www.jhipster.tech/documentation-archive/v5.0.2).

This is a "microservice" application intended to be part of a microservice architecture, please refer to the [Doing microservices with JHipster][] page of the documentation for more information.

This application is configured for Service Discovery and Configuration with the JHipster-Registry. On launch, it will refuse to start if it is not able to connect to the JHipster-Registry at [http://localhost:8761](http://localhost:8761). For more information, read our documentation on [Service Discovery and Configuration with the JHipster-Registry][].

## Software Required
 Java 8
 Maven
 MongoDB
 jhipster-registry

## Java version
 The project requires Java 8 installed.  
 Remember to set the `JAVA_HOME` to use the correct JDK
 
## Dependencies
 a. Core - 
 		git url- https://gitlab.kelltontech.net/vestige/core.git
 		
 **NOTE:** take pull in the same common service folder. 

## git
Create a folder where your want to setup your local project.
1. Open Git Bash
2. $ cd CreatedFolder
3. $ git clone https://gitlab.kelltontech.net/vestige/mscommon.git
4. $ cd common/  
(You are now in master)
5. $ git checkout dev

## Database setup
* Build a schema with name `common`  
  OR  
  Change database properties in `application.properties` as required.  
  Basically we should have an empty schema with configs specified in application.properties.   
  
**NOTE:** Don't create any tables(as JPA will take care of that) 

## Development

To start your application in the dev profile, simply run:

    ./mvnw


For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].



## Building for production

To optimize the common application for production, run:

    ./mvnw -Pprod clean package

To ensure everything worked, run:

    java -jar target/*.war


Refer to [Using JHipster in production][] for more details.

## Using services
*  Make service requests with token:
   server port - 8086 
   You can make get request for `/api/v1/{}`  with:  
   Content-Type as application/json  
   
   **NOTE:** `v1 - version` may be changed by `application.properties` file

## Testing

To launch your application's tests, run:

    ./mvnw clean test
### Other tests

Performance tests are run by [Gatling][] and written in Scala. They're located in [src/test/gatling](src/test/gatling).

To use those tests, you must install Gatling from [https://gatling.io/](https://gatling.io/).

For more information, refer to the [Running tests page][].


[Doing microservices with JHipster]: https://www.jhipster.tech/documentation-archive/v5.0.2/microservices-architecture/
[Using JHipster in development]: https://www.jhipster.tech/documentation-archive/v5.0.2/development/
[Service Discovery and Configuration with the JHipster-Registry]: https://www.jhipster.tech/documentation-archive/v5.0.2/microservices-architecture/#jhipster-registry
[Using JHipster in production]: https://www.jhipster.tech/documentation-archive/v5.0.2/production/
[Running tests page]: https://www.jhipster.tech/documentation-archive/v5.0.2/running-tests/
