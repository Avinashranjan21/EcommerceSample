# E-Commerce Sample App

This is a sample Android application that demonstrates the use of various modern Android libraries including Jetpack Compose, Hilt for dependency injection, Retrofit for networking, and Coil for image loading. The app fetches data from a remote API and displays it in a user-friendly interface.

## Features

- **Jetpack Compose**: A modern toolkit for building native UI in Android.
- **Hilt**: A dependency injection library that simplifies Dagger's setup.
- **Retrofit**: A type-safe HTTP client for Android and Java that simplifies networking tasks.
- **Coil**: An image loading library for Android backed by Kotlin Coroutines.

## Libraries Used

1. **Jetpack Compose**: 
   - A toolkit for building native UIs in Android. It allows you to define your UI in a declarative manner.
   - Documentation: [Jetpack Compose](https://developer.android.com/jetpack/compose)

2. **Hilt**:
   - A dependency injection library for Android that reduces boilerplate code by generating components for you.
   - Documentation: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

3. **Retrofit**:
   - A networking library that makes it easy to consume RESTful web services in Android.
   - Documentation: [Retrofit](https://square.github.io/retrofit/)

4. **Coil**:
   - An image loading library for Android that supports Kotlin Coroutines and provides a simple API for loading images.
   - Documentation: [Coil](https://coil-kt.github.io/coil/)

5. **OkHttp**:
   - An HTTP client that is used by Retrofit and provides built-in support for caching and network features.
   - Documentation: [OkHttp](https://square.github.io/okhttp/)

## Project Structure

- **app/src/main/java/com/example/ecommercesample**
  - `MyApplicationClass`: Application class annotated with `@HiltAndroidApp`.
  - `network/ApiService`: Interface defining the API endpoints.
  - `network/NetworkHelper`: Utility class to check network availability.
  - `network/NetworkModule`: Dagger Hilt module providing network-related dependencies.
  - `repository/MainRepository`: Repository class to handle data operations.
  - `ui/SectionListScreen`: Composable function displaying the list of sections.
  - `viewmodel/MainViewModel`: ViewModel for managing UI-related data in a lifecycle-conscious way.
## Screenshots

Add screenshots of the app below:

![image](https://github.com/user-attachments/assets/229ed7ab-9f22-4538-befa-c4929733a809)

