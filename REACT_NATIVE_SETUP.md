# React Native Setup Instructions

## Prerequisites

### 1. Install Node.js
- Download and install Node.js 16+ from [nodejs.org](https://nodejs.org/)
- Verify installation: `node --version` and `npm --version`

### 2. Install Java Development Kit (JDK)
- Download and install JDK 17 from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
- Set JAVA_HOME environment variable
- Verify installation: `java --version`

### 3. Install Android Studio
- Download from [developer.android.com](https://developer.android.com/studio)
- Install Android SDK (API level 33 or higher)
- Set ANDROID_HOME environment variable
- Add Android SDK tools to PATH

### 4. Install React Native CLI
```bash
npm install -g @react-native-community/cli
```

## Project Setup

### 1. Initialize React Native Project
```bash
# Create new React Native project
npx react-native init GeoGameNative --template react-native-template-typescript

# Navigate to project directory
cd GeoGameNative
```

### 2. Install Dependencies
```bash
# Install required packages
npm install react-native-maps react-native-vector-icons react-native-svg
npm install @react-navigation/native @react-navigation/stack
npm install react-native-screens react-native-safe-area-context
npm install react-native-gesture-handler
npm install react-redux redux redux-thunk redux-logger
npm install react-native-localize react-native-i18n

# Install iOS dependencies (if on macOS)
cd ios && pod install && cd ..
```

### 3. Copy Project Files
Copy all the files from this directory to your new React Native project:
- `App-rn.js` → `App.js`
- `src/` directory with all components
- `package-rn.json` → `package.json`

### 4. Android Configuration

#### Update android/app/src/main/AndroidManifest.xml
Add Google Maps API key:
```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_GOOGLE_MAPS_API_KEY" />
```

#### Update android/app/build.gradle
Add Google Play Services:
```gradle
dependencies {
    implementation 'com.google.android.gms:play-services-maps:18.1.0'
    implementation 'com.google.android.gms:play-services-location:21.0.1'
}
```

### 5. Get Google Maps API Key
1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select existing
3. Enable Maps SDK for Android
4. Create credentials (API Key)
5. Restrict the key to your app's package name

## Running the App

### 1. Start Metro Bundler
```bash
npx react-native start
```

### 2. Run on Android
```bash
npx react-native run-android
```

### 3. Run on iOS (macOS only)
```bash
npx react-native run-ios
```

## Building for Production

### Android APK
```bash
cd android
./gradlew assembleRelease
```

### Android App Bundle (AAB)
```bash
cd android
./gradlew bundleRelease
```

## Features

✅ **Complete Tajik Localization**
- All UI text in Tajik
- Country names and capitals in Tajik
- Default language set to Tajik

✅ **Native Android Performance**
- React Native components
- Native map integration
- Smooth animations

✅ **Geography Game Features**
- Interactive map gameplay
- Multiple game modes (Country Name, Capital, Flag)
- Score tracking and timer
- Multiple continents support

✅ **Language Support**
- Tajik (default)
- English
- Russian

## Troubleshooting

### Common Issues

1. **Metro bundler issues**: Clear cache with `npx react-native start --reset-cache`
2. **Android build issues**: Clean with `cd android && ./gradlew clean`
3. **Maps not showing**: Check Google Maps API key and permissions
4. **Language not changing**: Restart the app after changing language

### Performance Tips

1. Use release builds for testing performance
2. Enable Hermes for better performance
3. Optimize images and assets
4. Use FlatList for large lists

## Deployment

### Google Play Store
1. Generate signed APK/AAB
2. Create Google Play Console account
3. Upload and configure app listing
4. Submit for review

### Alternative Stores
- Amazon Appstore
- Samsung Galaxy Store
- Huawei AppGallery

## Support

For issues or questions:
1. Check React Native documentation
2. Review Google Maps documentation
3. Check GitHub issues for similar problems
