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
- IntelliJ IDEA (recommended)
- Java Development Kit (JDK) 11 or later
- Postman (for API testing)

## Tech Stack
- Language: Java
- IDE: IntelliJ IDEA
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

### 2. Open in IntelliJ IDEA
- Launch IntelliJ IDEA
- Select "Open" and navigate to the project directory
- Allow Gradle to sync and download dependencies

### 3. Features
- Authenticate with the Reddit API.
- Fetch top posts from any subreddit.
- Submit posts to a subreddit.
- Save responses to a local file for reference.

### 4. Usage
1. Run the app:
   ```bash
   java -jar RedditApp.jar


## IntelliJ IDEA Configuration

### Recommended Plugins
- Gradle
- Firebase
- Lombok
- Material Theme UI

### Gradle Configuration
Ensure your `build.gradle` files are configured correctly:

#### Project-level `build.gradle`- Sample
```groovy
buildscript {
    dependencies {
        classpath 'com.google.gms:google-services:4.3.15'
        classpath 'com.android.tools.build:gradle:7.4.2'
    }
}
```

#### App-level `build.gradle` - Sample
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

## API Testing with Postman

### Postman Setup
1. Download and install Postman from [postman.com](https://www.postman.com/downloads/)
2. Import the API collection for this project

### API Endpoints
We've created a comprehensive Postman collection to test the following endpoints:

#### Authentication
- `POST /auth/register` - User registration
- `POST /auth/login` - User login
- `POST /auth/logout` - User logout

#### Subreddits
- `GET /subreddits` - List all subreddits
- `GET /subreddits/{id}` - Get specific subreddit details
- `POST /subreddits` - Create a new subreddit

#### Posts
- `GET /posts` - Retrieve posts
-
- ![Sample Fetched Post](https://github.com/user-attachments/assets/ffca6c7c-0af7-4410-9740-47920b0a85a1)
- 
- `GET /posts/{id}` - Get specific post details
- `POST /posts` - Create a new post
- `PUT /posts/{id}` - Update a post
- `DELETE /posts/{id}` - Delete a post
- "Below is Sampled Fetched Post!!!!"


## Testing
- Unit Tests: Located in `src/test/java`
- Instrumentation Tests: Located in `src/androidTest/java`
- API Tests: Managed through Postman collection

Run tests in IntelliJ IDEA:
- Right-click on test directories
- Select "Run Tests"

## Debugging
IntelliJ IDEA provides powerful debugging tools:
- Set breakpoints
- Use step-through debugging
- Examine variables and call stack
- Use the integrated debugger for Android apps

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License
Distributed under the MIT License. See `LICENSE` for more information.

Project Link: [https://github.com/CodeCshekhar/Reddit-App](https://github.com/CodeCshekhar/Reddit-App)
