# Deployment Guide

## Railway Deployment

This project is ready for deployment on Railway. Follow these steps:

### 1. Prerequisites
- Railway account
- Git repository with the project code

### 2. Railway Setup
1. Go to [Railway.app](https://railway.app)
2. Connect your GitHub repository
3. Railway will automatically detect this as a Node.js project
4. The `railway.json` configuration file will handle the deployment settings

### 3. Environment Variables
No additional environment variables are required for basic deployment.

### 4. Build Process
Railway will automatically:
1. Install dependencies (`npm install`)
2. Build the project (`npm run build`)
3. Serve the static files using `npx serve -s build -l 3000`

### 5. Custom Domain (Optional)
- Go to your Railway project settings
- Add a custom domain if needed
- Railway will provide SSL certificates automatically

## Local Development

To run the project locally:

```bash
# Install dependencies
npm install

# Start development server
npm start

# Build for production
npm run build

# Serve production build locally
npm run serve
```

## Project Structure

- **Default Language**: Tajik (тоҷикӣ)
- **Available Languages**: English, Russian, Tajik
- **Build Output**: `build/` directory
- **Static Assets**: All assets are included in the build

## Notes

- The project uses React 15.6.2 with Create React App
- All localization files are included in the build
- The app defaults to Tajik language but allows switching to English or Russian
- No backend services required - this is a pure frontend application
