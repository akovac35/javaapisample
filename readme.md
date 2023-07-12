# Java Spring Boot API sample

This project contains a Java Spring Boot API sample. The following is demonstrated:

* authentication,
* authorization,
* mediator, command and query patterns,
* database repository pattern,
* transaction handling,
* error handling,
* model validation,
* unit testing,
* integration testing,
* some Swagger tricks,
* in-memory database,
* multiple database types,
* integration tests cover multiple databases,
* ...

To start the sample, run the following command in solution root:

* `./scripts/build_docker.sh`
* ` docker compose -f compose-[in-memory|sqlserver|oracle].yaml up`

Then navigate to `http://localhost:8080/swagger-ui/index.html` and first initialize sample data, including users for authentication. Examine command handler `si.zpiz.sample.infrastructure.initialization_related.InitializeSampleDataHandler` to understand how data is initialized.

Supported users using `password`:

* admin - full access
* user - not allowed to delete data
* unauthenticated users - may only initialize data or cretae token

Authorization header pattern: `<token>`

The following is TODO:

* localization,
* generating openapi.json during build,
* generating Java client from openapi.json during build,
* integration testing using generated .Java client,
* ...

## For development in VSCode

Install the following plugins:

* Extension Pack for Java from Microsoft,
* Spring Boot Extension Pack from VMware,
* Java 20
* Maven command line

Once plugins are installed, the project can be debugged from Spring Boot Dashboard and tests can be debugged from Testing dashboard. The `.vscode` folder contains settings which default to `h2` for running tests and debugging.

Additional commands:

* run tests with profile: `mvn test '-Dspring.profiles.active=h2'`
* run application with profile: `mvn spring-boot:run '-Dspring-boot.run.profiles=h2'` - Maven target has its own parameter to specify active Spring boot profile
* additional profiles are `oracle` and `sqlserver`

## For development in Eclipse

Install the following plugins:

* Spring Tools 4
* Project Lombok - https://projectlombok.org/setup/eclipse
* Java 20 with supported Eclipse 
* Maven command line