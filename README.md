# Geo Game - Native Android

A geography learning game built with native Android development, featuring interactive maps, multi-language support, and educational gameplay.

## Features

- 🌍 **Interactive Maps**: Google Maps integration with touch-to-select functionality
- 🎯 **Game Modes**: Country name, capital city, and flag identification
- 🌎 **Continents**: Play with countries from Africa, Asia, Europe, North America, South America, and Oceania
- 🕐 **Timer**: 30-second countdown per question
- 📊 **Scoring**: Track your progress and improve your geography knowledge
- 🌐 **Multi-language**: English, Russian, and Tajik support
- 🎨 **Material Design 3**: Modern Android UI components

## Technology Stack

- **Kotlin**: 100% native Android development
- **MVVM Architecture**: ViewModel + LiveData for reactive UI
- **Room Database**: Local data storage for country information
- **Hilt**: Dependency injection for clean architecture
- **Google Maps SDK**: Interactive map functionality
- **Material Design 3**: Modern Android design system
- **Coroutines**: Asynchronous programming

## Setup

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+ (Android 7.0)
- Google Maps API key

### Installation

1. **Get Google Maps API Key**:
   - Go to [Google Cloud Console](https://console.cloud.google.com/)
   - Enable the Maps SDK for Android
   - Create an API key

2. **Configure API Key**:
   - Open `android/app/src/main/AndroidManifest.xml`
   - Replace `YOUR_GOOGLE_MAPS_API_KEY` with your actual API key

3. **Open in Android Studio**:
   - Open the `android/` folder as a project
   - Let Gradle sync complete
   - Build and run the app

## Project Structure

```
android/
├── app/src/main/java/com/geogamenative/
│   ├── data/           # Room database and repository
│   ├── di/             # Hilt dependency injection
│   ├── ui/             # Activities (Start, Game)
│   ├── utils/          # Helper classes
│   ├── viewmodels/     # MVVM ViewModels
│   └── GeoGameApplication.kt
├── app/src/main/res/   # Layouts, strings, themes
└── build.gradle        # Dependencies
```

## Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture:

- **Model**: Room database with country data
- **View**: Activities and XML layouts
- **ViewModel**: Business logic and state management

### Key Components

- **Activities**: MainActivity, StartActivity, GameActivity
- **ViewModels**: MainViewModel, StartViewModel, GameViewModel
- **Database**: Room with Country entity and DAO
- **Repository**: Data access layer
- **Maps**: Google Maps integration for interactive gameplay

## Gameplay

1. **Select Language**: Choose from English, Russian, or Tajik
2. **Pick Game Mode**: Country name, capital, or flag identification
3. **Choose Continent**: Select from 6 continents
4. **Play**: Find countries on the map within the time limit
5. **Score**: Earn points for correct answers

## Development

### Building
```bash
cd android
./gradlew assembleDebug
```

### Testing
```bash
cd android
./gradlew test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.