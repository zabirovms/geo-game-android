@echo off
REM Android Build Test Script for Windows
REM This script tests the native Android build

echo 🚀 Starting Android Build Test...

REM Navigate to android directory
cd android

REM Clean previous builds
echo 🧹 Cleaning previous builds...
gradlew.bat clean

REM Check if all dependencies are resolved
echo 📦 Checking dependencies...
gradlew.bat dependencies --configuration debugRuntimeClasspath

REM Build debug APK
echo 🔨 Building debug APK...
gradlew.bat assembleDebug

REM Check if build was successful
if %ERRORLEVEL% EQU 0 (
    echo ✅ Build successful!
    echo 📱 APK location: app\build\outputs\apk\debug\app-debug.apk
    
    REM List APK details
    echo 📊 APK Details:
    dir app\build\outputs\apk\debug\
    
    echo.
    echo 🎉 Native Android conversion completed successfully!
    echo 📋 Next steps:
    echo 1. Get a Google Maps API key
    echo 2. Replace 'YOUR_GOOGLE_MAPS_API_KEY' in AndroidManifest.xml
    echo 3. Open project in Android Studio
    echo 4. Run on device or emulator
) else (
    echo ❌ Build failed!
    echo Please check the error messages above.
    exit /b 1
)
