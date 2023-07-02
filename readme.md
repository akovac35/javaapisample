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

* `mvn spring-boot:run`

Then navigate to: `http://localhost:8080/swagger-ui/index.html`

Supported users using `password`:

* admin - full access
* user - not allowed to delete data
* unauthenticated users - may only initialize data

Authorization header pattern: `<token>`

Examine command handler `si.zpiz.sample.infrastructure.initialization_related.InitializeSampleDataHandler` to understand how data is initialized.

The following is TODO:

* localization,
* generating openapi.json during build,
* generating Java client from openapi.json during build,
* integration testing using generated .Java client,
* ...

For development in VS Code install the following plugins:

* Extension Pack for Java from Microsoft,
* Spring Boot Extension Pack from VMware,
* ...

Once plugins are installed, the project can be debugged from Spring Boot Dashboard and tests can be debugged from Testing dashboard.

Additional commands:

* Run tests with profile: `mvn test '-Dspring.profiles.active=h2'`
* Run application with profile: `mvn spring-boot:run '-Dspring-boot.run.profiles=h2'`
