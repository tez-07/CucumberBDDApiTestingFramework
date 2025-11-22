🧪 BDD Cucumber Java RestAssured API Automation Framework
======================================================

This repository contains the BDD API automation framework I built using **Java**, **Cucumber**, **RestAssured**, and **Maven**.My goal was to create a clean, scalable, data-driven testing framework with reusable components, POJOs, hooks, tagging, and centralized API resource management.

📦 Project Setup
----------------

### 🔹  Creating the Maven Project

I started by creating a **Maven project without any archetype** and added the core dependencies needed for API automation:

*   **RestAssured**
    
*   **Cucumber**
    
*   **Cucumber-JUnit**
    
*   **Jackson** (to convert Java objects ↔ JSON)
    
*   **Apache Groovy** (a common library shared between Cucumber-JUnit and RestAssured)
        

🗂 Project Structure
--------------------
<img width="385" height="451" alt="image" src="https://github.com/user-attachments/assets/df331371-d17d-4d1e-8891-f48ed4acee7d" />


### Key Components in My Framework

#### ✔ Features

I placed all .feature files here, written in **Gherkin syntax**, describing the behavior of the APIs I wanted to test.

#### ✔ Cucumber Test Runner

This runner connects my:

*   Feature files
    
*   Step definitions
    
*   Cucumber reporting
    
*   Tag-based filtering
    

#### ✔ Step Definitions

These contain the actual implementation for each scenario step.

#### ✔ POJO Layer

Located under src/main/java, these classes help me handle payloads more efficiently by supporting both serialization and deserialization.

🧱 Framework Implementation Details
-----------------------------------

### 🔹 Request & Response Utilities

I created a **Utils** file that defines all reusable components:

*   RequestSpecBuilder with base URI, content type, query params, etc.
    
*   Logging for both **request** and **response**
    
*   Reusable response specifications
    

This helped me maintain consistency and reduce repeated code across multiple test scenarios.

🧬 Data-Driven Testing
----------------------

To drive dynamic input from the feature files, I used:

*   **Scenario Outline**
    
*   **Examples tables**
    

This allows me to:

*   Inject values from the feature file directly into the step definitions
    
*   Update POJO fields dynamically before sending the payload
    
*   Reuse the same test logic for multiple data sets
    

🧭 Enum for API Endpoints
-------------------------

I built an APIResources enum to manage all API endpoints in one centralized place.

This makes the framework more maintainable and reduces the risk of hard-coded strings.

🔖 Tagging Mechanism
--------------------

I used Cucumber tags (similar to TestNG grouping) to run specific sets of tests such as:

*   @Smoke
    
*   @Regression
    
*   @AddPlace
    
*   @DeletePlace
    

Tag filters are controlled directly from my TestRunner file.

🔁 Hooks (Before/After)
-----------------------

I implemented Cucumber hooks to handle setup and cleanup tasks.

Example:For the deletePlace API to work, I first need a place ID.So in the **@Before hook**, I ensured addPlace is executed when needed.

This helped in maintaining scenario dependencies without creating test flakiness.

📝 Maven Execution
------------------

I run the entire test suite using:

The TestRunner triggers all feature files and integrates reports into the Maven lifecycle.

📊 Reporting
------------

I configured the framework to generate:

*   Cucumber HTML reports (https://github.com/damianszczepanik/maven-cucumber-reporting)
    
*   Logs for debugging
    

This gives me a clear picture of which tests passed/failed and why.

📂 Test Data Management
-----------------------

I store:

*   JSON body templates
    
*   Sample payload files for AddPlaceAPI and others
    

I use:

*   **Setter methods** to update data dynamically
    
*   **Getter methods** in POJOs to access them cleanly

📂 Jenkins
-----------------------

###  Configure Git & Maven Globally

*   **Git:** Manage Jenkins → Global Tool Configuration → Git → add installation.
    
*   **Maven:** Manage Jenkins → Global Tool Configuration → Maven → add installation.
    

###  Add GitHub Credentials

*   Manage Jenkins → Credentials → System → Global → Add Credentials
    
*   Use **Username + Personal Access Token** and give an **ID** (e.g., github-credentials).
    

###  Configure SCM in Job

*   Job → Source Code Management → Git
    
*   Repo URL: https://github.com/user/repo.git
    
*   Credentials: select github-credentials
    
*   Branch: main (or relevant branch)
    

###  Build Step

*   Build → Add build step → Invoke top-level Maven targets
    
*   **Goals:** test
    
*   **Options:** -Dcucumber.options="--tags @AddPlace"

This approach keeps my payloads reusable and readable.


Thanks to : Rahul Shetty (https://rahulshettyacademy.com/)
