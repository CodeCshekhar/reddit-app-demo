# Reddit Clone App

## Overview
This is a Reddit-like application built using Android development with Gradle as the build system. The app provides key features inspired by the Reddit platform, allowing users to browse, interact with, and share content.

## Features
- User authentication and registration
- Subreddit browsing
- Post creation and viewing
- Commenting system
- Upvote/Downvote functionality
- User profiles
- Personalized feed

## Prerequisites
- Android Studio
- Java Development Kit (JDK) 11 or later
- Android SDK

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
git clone https://github.com/CodeCshekahr/Reddit-App.git
cd Reddit-App
```

### 2. Configure Firebase
- Create a Firebase project
- Add `google-services.json` to the `app` directory
- Enable Authentication and Firestore

### 3. Build the Project
Open the project in Intellij IDE and let Gradle sync the dependencies.

### 4. Run the App
- Connect an Intellij IDE 
- Click 'Run' in Intellij IDE 

## Gradle Configuration
The project uses Gradle for dependency management and build automation. Key configurations are in `build.gradle` files:

### App-level `build.gradle`
```groovy
plugins {
    id 'com.android.application'
    id 'dagger.hilt.android.plugin'
    id 'com.google.gms.google-services'
}

dependencies {
    // Android core dependencies
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.5.0'
    
    // Firebase
    implementation 'com.google.firebase:firebase-auth:21.0.3'
    implementation 'com.google.firebase:firebase-firestore:24.0.1'
    
    // Networking
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    
    // Dependency Injection
    implementation 'com.google.dagger:hilt-android:2.40.5'
    annotationProcessor 'com.google.dagger:hilt-android-compiler:2.40.5'
}
```

## Testing
- Unit Tests: Located in `src/test/java`
- Instrumentation Tests: Located in `src/Test/java`

Run tests using:
```bash
./gradlew test
./gradlew connectedAndroidTest
```

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License
Distributed under the MIT License. See `LICENSE` for more information.

Project Link: [https://github.com/CodeCshekahr/Reddit-App](https://github.com/CodeCshekahr/Reddit-App)
