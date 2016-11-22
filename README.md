# eventful-springboot-restful-angular
SpringBoot App to consume Eventful API and Weather ApI

This is a SpringBoot Maven project to consume Eventful and Open Weather API's. It uses Spring MVC,RestTemplate, Jackson JSON api to consume thrid party API.
The Form built in Angular JS filters the events by Category, City and filters by the event column names in the client front-end page.


## Running the program
To run the application:
```
mvn spring-boot:run
```

And browse to:
![http://localhost:8080/index.html](http://localhost:8080/index.html)
This will load up the application page.

The API key for the third party have been created and externalised into application.properties file.

## Microservice Acrhitecture
The application is split into tiers to give the non-functional property of loose coupling. 
Each microservice has it own tiers add. In this example I have created one for Events and the second its the Weather.
The front-end is an Angular JS embedded within the index.html page. It called the Spring MVC endpoint and renders the JSON output from the application into the view.

The category list is first loaded. Upon selecting a category and entering a location into the input box. A REST request is sent to the Spring MVC controllers which returns with a filtered list of events.
  The Angulars JS iterates through the JSON response and bind each field to the form and page HTML elements. This gives loosely coupled front-end which enables front-end developers to customise the application without have to modify the backend or restart the Spring Boot app service. 

## Application Tiers
These are the fours tiers
* Controllers - this defines the endpoint which the application request respone handlers will serve. It's responsible for directing the incoming request to the relevant service and responsed to the front-end, in this is a HTML page with Angular JS to render the results from the JSON output.
* Domain - This is the object oriented model of our entities. In this case it is Event, Category and Weather.
* Repository - This is the backend data access layers for third party data access.
* Serivce - This process the incoming request from the controllers calling upon the necessary data access code in the repository package.

## Properties
The application.properties contains properties relating to the third party API end point calls.

## File Structure

├── main
│   ├── java
│   │   └── com
│   │       └── dawud
│   │           ├── Application.java
│   │           ├── config
│   │           │   ├── EventfulRestClient.java
│   │           │   └── WebConfig.java
│   │           ├── events
│   │           │   ├── controllers
│   │           │   │   ├── CategoryController.java
│   │           │   │   └── EventController.java
│   │           │   ├── domain
│   │           │   │   ├── Category.java
│   │           │   │   ├── Event.java
│   │           │   │   └── util
│   │           │   │       └── EventConstants.java
│   │           │   ├── repository
│   │           │   │   ├── CategoryRepository.java
│   │           │   │   └── EventRepository.java
│   │           │   └── service
│   │           │       ├── CategoryService.java
│   │           │       └── EventService.java
│   │           └── weather
│   │               ├── controllers
│   │               │   ├── EventWeatherController.java
│   │               │   └── WeatherController.java
│   │               ├── domain
│   │               │   ├── EventWeather.java
│   │               │   └── Weather.java
│   │               ├── repository
│   │               │   └── WeatherRepository.java
│   │               └── service
│   │                   └── WeatherService.java
│   └── resources
│       ├── application.properties
│       ├── static
│       │   ├── app.js
│       │   └── index.html
│       └── templates
└── test
    └── java
        └── com
            └── dawud
                ├── ApplicationTests.java
                ├── events
                │   ├── repository
                │   │   ├── CategoryRepositoryTest.java
                │   │   ├── EventRepositoryTest.java
                │   │   └── WeatherRepositoryTest.java
                │   └── service
                │       ├── CategoryServiceTest.java
                │       └── EventServiceTest.java
                └── weather
                    ├── repository
                    │   └── WeatherRepositoryTest.java
                    └── service

