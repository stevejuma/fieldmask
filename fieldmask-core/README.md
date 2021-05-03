# Kotlin Template
A kotlin template to help with bootstrapping new services.

# TLDR;
* Change the name of the app by `./gradlew -PappName="awesome-app" updateAppName idea ktlintApplyToIdea`
* Update git hooks by `./hooks/install.sh`
* Start the app by `bin/docker dev-up` 
  * Or without docker using `./gradlew bootRun` 
* Navigate to http://localhost:8080 to view your app

## Template 
Update the app name by running the following command. Please do this
before importing the project into any IDE.

* Ensure you commit your pending git changes before executing the commands below. 

```bash
	# Where `app-name` is the new name of the app
	./gradlew -PappName="lucenequery-core" updateAppName idea ktlintApplyToIde
	
	# If you want to change the name again, you will need to specify the previous value
	./gradlew -PappName="kotlin-template" -PpreviousName="app-name" updateAppName idea ktlintApplyToIdea
```

## Running with docker ( Recommended )

* Execute `bin/docker dev-up` to create the images and bootstrap the
  stack
  * You can bring down the services by running `bin/docker down`
  * All other `docker-compose` commands should work normally, the 
    `bin/docker` script is provided here merely as a shortcut for commands 
    you would otherwise be running constantly.  

## Running with Gradle 

* You can build and execute the service by running `./gradlew bootRun` 
* You will need to specify all the parameters that your app requires 
  to be started. This will include any databases if any or refrences 
  to running instances of RabbitMQ if used. 
* You can specify configuration properties either as environment variables
  or as arguments to the `bootRun` command 


## Developing locally

Project has been developed with `IntelliJ IDEA CE`. To get the IDE
setup with the project you will need to follow the steps below.

* After cloning the project, change the name of the template as outlined 
  above, then execute `./gradlew idea`. This will create the relevant 
  project files for the IDE. 
* Open the IDE and import the project into it. It should recognise the 
  previously generated project files
* The IDE should create the `.idea` directory after the project has been
  imported. Now execute `./gradlew ktlintApplyToIdea` to apply the linter
  settings for the IDE to abide by. You will need to restart the IDE
  after this.
* Activate development git hooks by running `./hooks/install.sh`. This
  will add a ktlint hook that will check kotlin files against the linter
  before committing. It will also add a hook to update circle ci
  messages to ignore unnecessary builds. You can add files to be ignored
  for ci build by updating the `.ciignore` file

## Building 
Gradle is the build system that's being used in this template. A gradle
wrapper is provided at the root of this repository so you don't have to
install gradle locally. Gradle commands can be invoked as `./gradlew
<command>`

While gradle does have a kotlin DSL currently available, it's best to
avoid this for now. All the examples available online will always
reference the groovy dsl and it's a little cumbersome trying to retrofit
those to match the kotlin dsl.

