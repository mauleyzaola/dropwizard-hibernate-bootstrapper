#### This bootstrapper has been tested to work on Linux, MacOS and Windows with PostgreSQL 9.3

Since I am really no Java expert, I picked Dropwizard framework to work on an API web application. It is much simplier
to setup than J2EE, but still needs a little understanding of what's going on.

In case you're wondering how this can be done easily, just follow these instructions bellow. Enjoy.

## Instructions

* Make sure you have JDK 1.8, Maven 3.x and PostgreSQL 9.3 installed on your machine
* Download the code
* Create a PostgreSQL database
* Edit configuration.yml file accordingly (change username, password, url and database to match your env)
* On the command line type

```
mvn package
```

* Create database
```
psql -c "create database dw"
```

* Wait a while, Maven should download all the dependencies required on the pom.xml file
* When it's done, just run Dropwizard

```
java -jar target/dropwizard-bootstrapper-0.1.jar server configuration.yml
```

The application should create all the tables and start the API at [http://localhost:8080](http://localhost:8080)


### CURL Examples
Insert country (id is auto generated, no need to pass it on the request)
```
$ curl -XPUT -s --header "Content-Type:application/json"  http://localhost:8080/country -d'{"isoCode":"mx","name":"Mexico"}' | jq .
{
  "id": "8b5db2c7-f503-427e-9a8e-3dabd1240437",
  "name": "Mexico",
  "isoCode": "MX",
  "dateCreated": "2017-11-30T09:53:06.830-06:00",
  "lastModified": null
```

Select country
```
~$ curl -s http://localhost:8080/country/8b5db2c7-f503-427e-9a8e-3dabd1240437
{
  "id": "8b5db2c7-f503-427e-9a8e-3dabd1240437",
  "name": "Mexico",
  "isoCode": "MX",
  "dateCreated": "2017-11-30T09:41:28.176-06:00",
  "lastModified": null
}
```

Update country
```
~$ curl -XPOST -s --header "Content-Type:application/json"  http://localhost:8080/country/8b5db2c7-f503-427e-9a8e-3dabd1240437 -d'{"isoCode":"br","name":"Brazil"}' | jq .
{
  "id": "8b5db2c7-f503-427e-9a8e-3dabd1240437",
  "name": "Brazil",
  "isoCode": "BR",
  "dateCreated": "2017-11-30T09:41:28.176-06:00",
  "lastModified": "2017-11-30T09:45:27.321-06:00"
}
```

Delete country
```
~$ curl -XDELETE -s --header "Content-Type:application/json"  http://localhost:8080/country/8b5db2c7-f503-427e-9a8e-3dabd1240437 | jq .
{
  "id": "8b5db2c7-f503-427e-9a8e-3dabd1240437",
  "name": "Brazil",
  "isoCode": "BR",
  "dateCreated": "2017-11-30T09:41:28.176-06:00",
  "lastModified": "2017-11-30T09:45:27.321-06:00"
}
```