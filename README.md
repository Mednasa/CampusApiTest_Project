# Campus Mersys RestApi Project 

**Two Week Sprint**

This repository contains the automated testing suite for the CampusMersys project, a web-based platform designed for educational institutions.
The tests cover various features available on the [Campus](https://test.mersys.io/) (test.mersys.io) website.

The Environment Used in the Project: [test.mersys.io](https://test.mersys.io/)

## Table of Contents

- [Project Overview](#project-overview)
- [Features Tested](#features-tested)
- [Technologies and Tools Used](#technologies-and-tools-used)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Contributors](#contributors)
- [Team Members](#team-members)
- [Contributing](#contributing)
- [Pull Request Rules](#pull-request-rules)
- [License](#license)

## Project Overview

Campus is an educational platform that provides a range of features for students to manage their academic activities. This testing project is focused on ensuring the reliability and functionality of the platform's features, including user registration, course management, assignment submissions, and more.

## Features Tested

The following user stories are tested in this project:

1. **Login (US-001):** As a user, I would like to be able to log in to the admin role in the test environment via API so that I can safely perform administrative functions that require control by obtaining a token..

2. **Setup> Parameters> Country (US-002):**  As a user, I want to add a country to the system that has a state through API. This will allow me to verify that all the necessary information about countries and states is correct in the response.

3. **Setup > Parameters > State (US-101):** As a user, I want to perform CRUD (Create, Read, Update, Delete) functions for states via API. This will enable me to list state information, search for a specific state, add a new state, update an existing state, and delete a state.

4. **Setup > Parameters > Cities (US-102):**  As a user, I aim to leverage CRUD operations for cities via API. This comprehensive functionality will empower me to seamlessly list city details, fetch information about a specific city, add a new city, update an existing city, and remove a city when necessary.

5. **Entrance Exam > Setup > Entrance Exam (US-103):** As a user, I desire the ability to manage exams for a course via API, encompassing the creation, listing, editing, and deletion operations (CRUD). This will enable me to assess students effectively and provide feedback.

6. **Entrance Exam > Setup > Custom Field Group (US-104):**  As a user, I want to customize the entrance exam by adding, editing, and deleting fields, providing me with additional arguments to adjust and analyze the exam content and difficulty.

7. **Student > Student Groups (US-105):** As a user, I aim to create, update, delete, and list student groups via an API. This will allow students to collaborate within groups based on their interests.

8. **Student > Student Groups (US-106):** As a user, I want to be able to add students to student groups via API. This will enable me to register interested students into their desired groups.

9. **Student > Student Groups (US-107):** As a user, I want to be able to remove students from student groups via API. This will allow students to leave the group at their discretion.

10. **Education > Setup > Education Standard (US-108):** As a user, I want to be able to edit Education Standards via API (Add, Edit, Delete, List = CRUD). This will make it easier for me to track which standards are applied to which courses in the school.

11. **Education > Setup > Grading Scheme (US-109):** As a user, I want to be able to edit the grading scheme via API (Add, Edit, Delete, List = CRUD). This will allow the school to transition more easily between changing grading systems.

12. **Incident > Setup > Incident Type (US-110):** As a user, I want to be able to define and edit incidents such as accidents that may occur in the school via API (Add, Edit, Delete, List = CRUD). This will enable me to create a statistical dataset and take preventive measures.



## Technologies and Tools Used

The basic technologies and tools to be used in this project include:

- **Java** - Programming language.
- **TestNG** - Testing framework.
- **Rest Assured** - Rest Assured is a Java library for testing and validating RESTful web services.
- **Hamcrest** -Hamcrest is a framework for writing matcher objects, allowing 'match' rules to be defined declaratively
- **Jackson** -Jackson is a high-performance JSON processor for Java.
- **Java Faker** - Library for generating fake data.
- **Maven** - Dependency management and Build
- **Slf4j** - Simple logging facade for java 
- **Git & GitHub** - Version control Systems 
- **Page Object Model (POM)** - Test Automation Pattern


## Prerequisites

- Java Development Kit (JDK): Installed on your system for Java code writing and compilation.

- IDE (Integrated Development Environment): Choose IntelliJ IDEA, Eclipse, or NetBeans for Java development.

- Maven: Installed for dependency management and project build tasks.

- Git: Installed for version control, essential for managing your project's source code on GitHub.

- TestNG: Understand its annotations and how to write test cases using this framework.

- Rest Assured: Simplifies the process of making HTTP requests and assertions on the responses, often used in API testing.

- Hamcrest: Provides a library of matchers for writing readable and expressive test assertions, often used with TestNG and JUnit.

- Jackson: Used for converting Java objects to JSON and vice versa, facilitating JSON parsing and serialization in tests.

- Page Object Model (POM): Understand and implement this pattern in your test automation framework.

-Java Faker: Used to create dummy data for testing purposes, ensuring tests have realistic data inputs without needing actual data. 

- Logging with slf4j: Learn to configure and use for logging in your Java project.

## Project Structure
```
C:.
│   .gitignore
│   configuration.properties
│   pom.xml
│
├───.idea
│       .gitignore
│       compiler.xml
│       encodings.xml
│       jarRepositories.xml
│       misc.xml
│       vcs.xml
│       workspace.xml
│
└───src
    ├───main
    │   ├───java
    │   └───resources
    └───test
        └───java
            ├───campus
            │   ├───cities
            │   │       CampusCitiesTest.java
            │   │
            │   ├───country
            │   │       CampusCountryTest.java
            │   │
            │   ├───customFieldGroup
            │   │       CampusCustomFieldGroupTests.java
            │   │
            │   ├───educationStandart
            │   │       CampusEducationTests.java
            │   │
            │   ├───entranceExam
            │   │   │   CampusEntranceExamTests.java
            │   │   │
            │   │   └───model
            │   │           EmailMessage.java
            │   │           Exam.java
            │   │           GradeLevel.java
            │   │
            │   ├───gradingScheme
            │   │       CampusGradingSchemeTests.java
            │   │
            │   ├───incidentType
            │   │       CampusIncidentTypeTests.java
            │   │
            │   ├───loginNegative
            │   │       LoginNegative.java
            │   │
            │   ├───loginPositive
            │   │       LoginPositive.java
            │   │
            │   ├───states
            │   │       CampusStatesTest.java
            │   │
            │   └───studentGroups
            │       ├───group
            │       │       CampusGroupTests.java
            │       │
            │       └───student
            │               CampusStudentTests.java
            │
            └───utilities
                    ConfigReader.java

```

## Contributors
| User Stories                                     | Contributor                  |
|------------------------------------------------|------------------------------|
| [US001]| [Beyza Nur Er](https://github.com/beyzanurer) |
| [US107-US110]| [Ahmet Kaya](https://github.com/0AhmetKaya0)|
| [US002-US101-US103-US106-US108] | [Onur Girgin](https://github.com/Mednasa) |
| [US102-US105] | [Nigar Ayla Özcanan](https://github.com/NigarAylaOzcanan) |
| [US104-US109] | [Erdem Gürel](https://github.com/artam109) |



## Team Members
| Name                    | Role            | GitHub                                           | 
|-------------------------|-----------------|--------------------------------------------------|
| Beyza Nur Er            | QA Automation Engineer                  | [Beyza Nur Er](https://github.com/beyzanurer)   | 
| Nigar Ayla Özcanan      | QA Automation Engineer                  | [Nigar Ayla Özcanan](https://github.com/NigarAylaOzcanan) | 
| Ahmet Kaya              | QA Automation Engineer                  | [Ahmet Kaya](https://github.com/0AhmetKaya0) | 
| Erdem Gürel             | QA Automation Engineer                  | [Erdem Gürel](https://github.com/artam109)       |    
| Onur Girgin             | Project Lead & QA Automation Engineer   | [Onur Girgin](https://github.com/Mednasa) |

## Contributing
Contributions are welcome! If you find any issues or want to add more tests, feel free to open a pull request.
Please follow these steps to contribute:

- Fork the repository.
- Create a new branch for your feature or bugfix.
- Make your changes and commit them.
- Push to your branch.
- Create a pull request.

## Pull Request Rules

When contributing to this project, please adhere to the following guidelines:

1. **Create a Descriptive Pull Request Title**: Ensure that your pull request title clearly describes the changes you're making.

2. **Provide Detailed Descriptions**: In your pull request description, provide a clear and detailed explanation of the changes you've made and why they are necessary.

3. **One Pull Request per Feature**: If you're adding multiple features or making several changes, please submit each as a separate pull request.

4. **Follow Code Standards**: Maintain code cleanliness and adhere to the coding standards used in the project.

5. **Test Your Changes**: Before submitting a pull request, test your changes thoroughly to ensure they work as expected and do not introduce any regressions.

6. **Update Documentation**: If your changes impact any documentation, make sure to update it accordingly.

7. **Resolve Conflicts**: If there are any conflicts with the base branch, resolve them before submitting your pull request.

8. **Request Reviews**: Assign reviewers to your pull request to ensure that your changes are properly reviewed before merging.

9. **Be Responsive**: Respond promptly to any feedback or comments on your pull request.

10. **Ensure CI/CD Passes**: Ensure that all continuous integration (CI) checks pass before merging your pull request.

## License

This project is open-sourced software licensed under the [MIT license](https://opensource.org/licenses/MIT).


