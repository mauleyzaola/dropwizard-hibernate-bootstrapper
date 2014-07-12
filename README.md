####This bootstrapper has been tested to work on Linux, MacOS and Windows with PostgreSQL 9.3

Since I am really no Java expert, I picked Dropwizard framework to work on an API web application. Is is much simplier
to setup than J2EE, but still needs a little understanding of what's going on.

In case you're wondering how this can be done easily, just follow these instructions bellow. Enjoy.

##Instructions

* Make sure you have JDK 1.8, Maven 3.x and PostgreSQL 9.3 installed on your machine
* Download the code
* Create a PostgreSQL database
* Edit configuration.yml file accordingly (change username, password, url and database to match your env)
* On the command line type

```
mvn package
```

* Wait a while, Maven should download all the dependencies required on the pom.xml file
* When it's done, just run Dropwizard

```
java -jar target/dropwizard-bootstrapper-0.1.jar server configuration.yml
```

The application should create all the tables and start the API at http://localhost:8080

