## Standalone Web Framework Kit - NitroWebCage

   NitroWebCage - Model framework which uses NitroWebKit library for Web Testing.

   This Framework consists of defined Page Object Model structure and sample feature file for the users to extend their Test scripts.


---

## Santander Test Automation Framework Setup Guide

## Table of Contents
* __1. Capabilities provided by the Framework__
* __2. Setup__
* __3. Setup Existing Projects__
* __4. How to add the framework as a dependency__
* __5. Documentation__
* __6. Support__

### 1. Capabilities provided by the Framework
* 1.1 Ability to write test for Web
* 1.2 Execute tests on remote TaaS Browsers
* 1.3 Parallel Execution of Cucumber scenarios





### 2. Setup

* Setup your standalone project
* How to consume the Library

    ### 2.1 Setup your standalone project
    Pre-Requisites
    ## Java:
        Install JDK and set JAVA_HOME variable by following the below instructions
        https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/

    ## Maven:
        Download the maven from https://maven.apache.org/download.cgi
        Install the Maven following the instructions https://maven.apache.org/install.html

    ## Git:
        Download the git from https://git-scm.com/download/win
        Install the git and copy the path then follow the below instructions
        A.	Right-Click on My Computer
        B.	Click Advanced System Settings
        C.	Click Environment Variables
        D.	Then under System Variables look for the path variable and click edit
        E.	Add the path to git’s bin and cmd at the end 

    ## Forking the skeleton framework:
        1. Open the project location from the below URL
            https://uk-gitlab.almuk.santanderuk.corp/Testing_Community/NitroWebCage
        2. Click the fork image to fork the project
        3. Create a development branch from the fork to work
        4. Clone the repo into your local environment using
            git clone https://uk-gitlab.almuk.santanderuk.corp/Testing_Community/NitroWebCage.git
        5. Now checkout the branch and work on the branch
            git checkout -b branchname


    ## Build:
        1. To build the project run the below command from the project root directory.
            mvn clean compile -DskipTests -s settings.xml -Djavax.net.ssl.trustStore=nexus.jks -Djavax.net.ssl.trustStorePassword=changeit
        2. Once the Build is success
        3. Run the sample test by running the below command from the project root directory
            mvn test -Dbrowser=chrome -s settings.xml -Djavax.net.ssl.trustStore=nexus.jks -Djavax.net.ssl.trustStorePassword=changeit

            (Note: Make sure the right version of chrome driver is in /browserdrivers/chromedriver.exe
            Check the compatibility https://sites.google.com/a/chromium.org/chromedriver/downloads )

        It should open the chrome browser and test passed

    ## Pushing to project space:
        1. git branch --set-upstream-to=origin/master master
        2. git add .
        3. git commit –m “Initial Commit”
        4. git push -u origin master

    ### 2.2 How to consume the Library
        1. By adding the Nitroweb dependency to the pom.xml. Please refer to section 4 (How to add the framework as a dependency) it will download the jar for the version specified from nexus artifactory repository to the local .m2 directory {user.home}/.m2/repository
            location of the jar will be in  {user.home}/.m2/repository/com/test/Santander/automation/framework/nitrowebkit/
        2. It’s very simple structure and to add cucumber feature files  follow below steps

        Features: Cucumber feature files can be added in resources/features/<PROJECT_NAME>

        Pages: All the page locators and methods can be written under com/test/pages directory

        Steps: Step Definitions have to be under com/test/stepDefs

        The package name of step definitions should be added to TestRunner
            @RunWith(Cucumber.class)
            @CucumberOptions(
            monochrome = true,
            plugin = {"pretty" ,"html:target/cucumber.html" ,
                    "json:target/cucumber-report.json" ,
                    "junit:target/cucumber.xml",
                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
            glue = { "classpath:com/test/stepDefs","classpath:com/test/screenshothook","classpath:com/test/hooks"},
            features = "classpath:features" )
            public class TestRunner { }

        And all the directories should be in base package name called com.test



* __While running it from locally:__

    1. To build the project run the below command from the project root directory.
            mvn clean compile -DskipTests -s settings.xml -Djavax.net.ssl.trustStore=nexus.jks -Djavax.net.ssl.trustStorePassword=changeit
        2. Once the Build is success
        3. Run the sample test by running the below command from the project root directory
            mvn test –Dcucumber.options="—tags @sampleTest" –Dbrowser=chrome -s settings.xml -Djavax.net.ssl.trustStore=nexus.jks -Djavax.net.ssl.trustStorePassword=changeit

* __While running it from Jenkins:__

    1. Change the new project URL in Jenkins configuration or in pipeline script
    2. Add the below command in pipeline script STAGE where tests are running
                def scOutput = sh(script: 'mvn test {POMPATHDIR}/ -Dcucumber.options="--tags @health-check" -Dgrid.run=true -Dbrowser="internet explorer" -Djavax.net.ssl.trustStore=${WORKSPACE}/nexus.jks -Djavax.net.ssl.trustStorePassword=changeit', returnStatus: true

    For more details running from Jenkins please refer to https://confluence.almuk.santanderuk.corp/display/TAAS/Java+Framework+-Jenkins++Pipeline


## 3. Setup Existing Projects

1. After completing all the above steps move the existing features, steps and pages to the relevant directory as specified in the structure.
   * Suggested tests folder structure:

```
    |browserdrivers
    |   |_ {chromedriver}
    |   |_ {firefoxdriver}
    |   |_ {geckodriver}
    |   |_ {iedriver}
    |src
    |   |_test
    |     |_ java.com.test
    |     |              |_ stepDefs
    |     |              |  |_ {step_definitions}
    |     |              |_ pages
    |     |              |   |_{page classes}
    |     |              |
    |     | _ {TestRunner class}
    |     |
    |     |_resoruces
    |        |_features.{projectname}
    |          |_{feature files}
    |
```
3. Existing property files should be moved into /resources/properties


## 4. How to add the framework as a dependency
The following framework pakages are available in the Nexus Repository Manager. Refer: https://nexus.almuk.santanderuk.corp/#browse/search=keyword%3Dautomation:7f6379d32f8dd78fc6fd1fcd7de66d6d

* __Web maven dependency__
        ```
        <dependency>
            <groupId>com.test.santander.automation.framework</groupId>
            <artifactId>NitroWebKit</artifactId>
            <version>4.0.0</version>
        </dependency>
        ```

* Refer the nexus: https://nexus.almuk.santanderuk.corp/#browse/search=keyword%3Dautomation


## 5. Documentation
* __[User Manual](https://confluence.almuk.santanderuk.corp/display/TAAS/TaaS+Services+-+User+Guide)__
* https://confluence.almuk.santanderuk.corp/display/TAAS/TaaS+Automation+Frameworks
* https://confluence.almuk.santanderuk.corp/display/TAAS/TaaS+Performance+Service
* https://confluence.almuk.santanderuk.corp/display/TAAS/How+to+setup+custom+capabilities

## 6. Support
    For any support please contact TaaS at TaaSSupport@produban.co.uk






