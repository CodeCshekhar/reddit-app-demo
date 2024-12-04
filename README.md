# Reddit Clone App

## Overview
This is a Reddit-like application built using App development with Gradle as the build system. The app provides key features inspired by the Reddit platform, allowing users to browse, interact with, and share content.

## Features
- User authentication and registration
- Subreddit browsing
- Post creation and viewing
- Commenting system
- Upvote/Downvote functionality
- User profiles
- Personalized feed

## Prerequisites
- Intellij IDE
- Java Development Kit (JDK) 11 or later

## Tech Stack
- Language: Java
- Build Tool: Gradle
- Architecture: MVVM (Model-View-ViewModel)
- Database: Room Persistence Library
- Networking: Retrofit
- Dependency Injection: Dagger Hilt
- Image Loading: Glide

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/CodeCshekhar/Reddit-App.git
cd Reddit-App
```
### 2. Build the Project
Open the project in Intellij IDE and let Gradle sync the dependencies.

### 3. Run the App
- Connect an Intellij IDE 
- Click 'Run' in Intellij IDE 

## Gradle Configuration
The project uses Gradle for dependency management and build automation. Key configurations are in `build.gradle` files:

### App-level `build.gradle`
```groovy
plugins {
    id 'application'
    id 'java'
}

group 'com.example'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.squareup.okhttp3:okhttp:4.11.0'
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.2'
}

application {
    mainClass = 'com.example.RedditApp'
}
```

## Testing
- Unit Tests: Located in `src/test/java`
- Instrumentation Tests: Located in `src/Test/java`

Run tests using:
```bash
./gradlew RedditTestApp
```

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License
Distributed under the MIT License. See `LICENSE` for more information.

Project Link: [https://github.com/CodeCshekhar/Reddit-App](https://github.com/CodeCshekhar/Reddit-App)
