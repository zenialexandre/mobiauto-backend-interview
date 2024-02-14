# Mobiauto System
## Description
The Mobiauto System is a complete vehicle sales and resale system focusing on bringing a transparent, safe and convenient process to the final user. <br>
Having the main features as follows: <br>
### Resale Management
Registration, updating and exclusion of vehicle resales. <br>
### Opportunity Management
Registration, updating and exclusion of vehicle sales opportunities. <br>
### Service and Editing Opportunities
Carry out full service for each opportunity registered in the system, by the appropriate assistant from the appropriate store. <br>

---------------------------------------------

## Overview
### Software Prerequisites
The application makes usage of a containerized environment, using PostgreSQL as the database and pgAdmin4 for its proper administration. <br>
If you want to run the project locally on Windows 10/11, we recommend installing <strong>Docker Desktop</strong>. <br> <br>

Libraries and frameworks used: Springframework, Springdoc, Guava, Lombok, Hibernate and JUnit. <br>
Build automation tool: Gradle with Groovy plugin. <br>

### Microservices
The application is divided between microservices with different responsabilities.

#### system-administration
Responsible for the main services and commands above the users, profiles and roles of the system. <br>
Responsible for the common methods above the security of the system.

#### store-management
Responsible for the main services and commands above the stores of the system.

#### resale-managemnet
Responsible for the main services and commands above the resales of the system.

#### opportunity-management
Responsible for the main services and commands above the opportunities of the system.

#### serive-editing-opportunities
Responsible for the main services and commands above the attendance of the opportunities of each store.

### Architecture Diagram

![mobiauto-diagram drawio](https://github.com/zenialexandre/mobiauto-backend-interview/assets/84157233/467cd0f2-8cb2-4b72-bda1-1c4810f4d312)

---------------------------------------------

## Configuration and Environment Variables

### Default User on First Run
When the application is runned the first time, the system Roles, a default Profile and a default User are created. <br> <br>
The default User credentials are: <br>

<strong>
Email: admin@gmail.com <br>
Password: admin <br> <br>
</strong>

This default User has a Profile with the ADMINISTRATOR Role, being able to do every operation inside the system.

### Dependecies
The dependencies to run this application are as follows:

```gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.hibernate.validator:hibernate-validator'
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation "org.springframework.boot:spring-boot-starter-security"
	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
	implementation 'com.google.guava:guava:30.1-jre'
	testImplementation 'org.junit.jupiter:junit-jupiter:5.9.2'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-docker-compose'
	runtimeOnly 'org.postgresql:postgresql'
	annotationProcessor 'org.projectlombok:lombok'
}
```

### Environment Variables
JAVA_HOME = The path to your JDK, example: 'C:\Users\Dell\.jdks\openjdk-21'

---------------------------------------------

## Contact and Support
The main communication channel is through the following email: <strong>zenialexandre123@gmail.com</strong>
