#!/bin/bash

# Android Build Test Script
# This script tests the native Android build

echo "🚀 Starting Android Build Test..."

# Navigate to android directory
cd android

# Clean previous builds
echo "🧹 Cleaning previous builds..."
./gradlew clean

# Check if all dependencies are resolved
echo "📦 Checking dependencies..."
./gradlew dependencies --configuration debugRuntimeClasspath

# Build debug APK
echo "🔨 Building debug APK..."
./gradlew assembleDebug

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
    echo "📱 APK location: app/build/outputs/apk/debug/app-debug.apk"
    
    # List APK details
    echo "📊 APK Details:"
    ls -la app/build/outputs/apk/debug/
    
    echo ""
    echo "🎉 Native Android conversion completed successfully!"
    echo "📋 Next steps:"
    echo "1. Get a Google Maps API key"
    echo "2. Replace 'YOUR_GOOGLE_MAPS_API_KEY' in AndroidManifest.xml"
    echo "3. Open project in Android Studio"
    echo "4. Run on device or emulator"
    
else
    echo "❌ Build failed!"
    echo "Please check the error messages above."
    exit 1
fi
