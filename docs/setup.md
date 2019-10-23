# Setup

The following instructions will help you install a Java/Kotlin development environment from scratch. If you already have a Java/Gradle/Git environment installed that you are familiar with, then you can simply clone the repository and work in your existing environment.

For Kotlin development, we recommend using IntelliJ in all cases. The directions below for the other IDEs will probably get you going, but trust us - make your life easier and work with IntelliJ for Kotlin.

## For all Environments
Install and configure Git. Some of these steps are optional depending on the IDE you choose and your preferred workflow. If you have experience with these tools then you may feel free to alter the instructions to fit your preferences.

Install Git from [https://git-scm.com/](https://git-scm.com/)

## Setup for IntelliJ
1. Install IntelliJ Community Edition from [https://www.jetbrains.com/idea/download](https://www.jetbrains.com/idea/download)
1. Clone the repository from [https://github.com/jeffgbutler/microservice-workshop-kotlin.git](https://github.com/jeffgbutler/microservice-workshop-kotlin.git), or download the zip from [https://github.com/jeffgbutler/microservice-workshop-kotlin/archive/master.zip](https://github.com/jeffgbutler/microservice-workshop-kotlin/archive/master.zip)
1. Open IntelliJ, make a new empty project
1. Import 5 Gradle projects from the "/exercises" directory in the cloned repo (File->New->Module From Existing Sources...)
1. Run the unit tests in the `movie-award-service` - they should all pass
1. If you want to experiment with the Swagger UI, follow these steps:
    1. Start the web service in the `movie-award-service` project by navigating to `microservice.workshop.movieawardservice.MovieAwardServiceApplication.kt` and running the main method in the class
    1. Once the web service starts, open a browser and navigate to [http://localhost:8083](http://localhost:8083). You should see the Swagger UI for this web service. You can run a few queries to return some sample data (ids from 1 to 20 will return data)
    1. After you have shown that the service works, terminate the program

## Setup for Eclipse
1. Install JDK 8 or higher from [http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
1. Install Eclipse from [http://www.eclipse.org/downloads/](http://www.eclipse.org/downloads/). For these exercises the "Eclipse IDE for Java Developers" package is sufficient.
1. Clone the repository from [https://github.com/jeffgbutler/microservice-workshop-kotlin.git](https://github.com/jeffgbutler/microservice-workshop-kotlin.git), or download the zip from [https://github.com/jeffgbutler/microservice-workshop-kotlin/archive/master.zip](https://github.com/jeffgbutler/microservice-workshop-kotlin/archive/master.zip)
1. Open Eclipse, make a new workspace
1. Install the Buildship Gradle Support Plugin
1. Install the Kotlin Plugin
1. Import 5 projects from the "/exercises" directory in the cloned code repo (File->Import...->Existing Gradle Project)
1. Run the unit tests in the `movie-award-service` - they should all pass
1. If you want to experiment with the Swagger UI, follow these steps:
    1. Start the web service in the `movie-award-service` project by navigating to `microservice.workshop.movieawardservice.MovieAwardServiceApplication.java` and running the class as a Java application
    1. Once the web service starts, open a browser and navigate to [http://localhost:8083](http://localhost:8083). You should see the Swagger UI for this web service. You can run a few queries to return some sample data (ids from 1 to 20 will return data)
    1. After you have shown that the service works, terminate the program
 
## Setup for Visual Studio Code
Note: the following instructions setup a VS Code workspace with the four exercise projects. I prefer this method of setting up VS Code as it seems to work seemlessly with the different Java based extensions in VS Code. If you have a different preference, please feel free to use it.

1. Install JDK 8 or higher from [http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

Your life will be easier if you set the JAVA_HOME environment variable. There are a variety of ways to do this. If you are on Windows, then the easiest way to do this is to use the control panel applet to edit environment variables. We suggest you make these changes to the "user" environment variables and not the "system" environment variables.

| Environment Variable | Setting |
|----------------------|---------|
| JAVA_HOME| Set to the home directory of your JDK installation (for example, \Program Files\Java\jdk-11.0.1 |

1. Install VS code from [https://code.visualstudio.com/](https://code.visualstudio.com/)
1. Open VS code either with the `code` command, or through the UI (Note: do not simply open VS Code on the "exercises" folder. The Java extensions currently have difficulties with maven projects in sub-directories.)
1. Open the extensions page (ctrl-shift-X), install the Java Extension Pack (from Microsoft) if you haven't already installed that extension
1. Install the SpringBoot extension
1. Install the Kotlin extension
1. Clone the repository from [https://github.com/jeffgbutler/microservice-workshop-kotlin.git](https://github.com/jeffgbutler/microservice-workshop-kotlin.git), or download the zip from [https://github.com/jeffgbutler/microservice-workshop-kotlin/archive/master.zip](https://github.com/jeffgbutler/microservice-workshop-kotlin/archive/master.zip)
1. Add the 5 folders under the "/exercises" directory in the cloned repo to the VS Code workspace (File->Add Folder to Workspace...)
1. Save your workspace file somewhere convenient (File->Save Workspace As...)
1. If you get the message from VS Code that it can't find the Java Runtime...
   - ctrl-shift-P (show all commands)
   - Open Workplace settings
   - Add setting "java.home": "\<your JDK location\>" (for example "C:\\\\Program Files\\\\Java\\\\jdk-10.0.1")
   - Reload the window (ctrl-shift-P, then "Reload Window")
1. Run the tests in the `movie-award-service` project with the VS Code test explorer - they should all pass
1. If you want to experiment with the Swagger UI, follow these steps:
    1. Open a terminal window at the `movie-award-service` folder (right click on the folder, then "Open In Terminal")
    1. Start the web service with Maven:
        - (Linux/Mac) ./gradlew clean bootRun
        - (Windows) gradlew clean bootRun
    1. Once the web service starts, open a browser and navigate to [http://localhost:8083](http://localhost:8083). You should see the Swagger UI for this web service. You can run a few queries to return some sample data (ids from 1 to 20 will return data)
    1. After you have shown that the service works, you can end the service by pressing ctrl-c in the terminal window
