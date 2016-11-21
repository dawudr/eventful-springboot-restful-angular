# eventful-springboot-restful-angular
SpringBoot App to consume Eventful API and Weather ApI

This is a SpringBoot Maven project to consume Eventful and Open Weather API's. It uses Spring MVC,RestTemplate, Jackson JSON api to consume thrid party API.
The Form built in Angular JS filters the events by Category, City and filters by the event column names in the client front-end page.

To run the application:
mvn spring-boot:run

And browser to:
http://localhost:8080/index.html
This will load up the application page.

The API key for the third party have been created and externalised into application.properties file.


