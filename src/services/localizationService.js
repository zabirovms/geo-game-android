import I18n from 'react-native-i18n';
import {NativeModules, Platform} from 'react-native';

// Import translations
import en from '../locales/en.json';
import ru from '../locales/ru.json';
import tg from '../locales/tg.json';

// Configure translations
I18n.translations = {
  en,
  ru,
  tg,
};

// Set default locale to Tajik
I18n.defaultLocale = 'tg';

// Get device locale
const getDeviceLocale = () => {
  let locale = 'tg'; // Default to Tajik
  
  if (Platform.OS === 'ios') {
    locale = NativeModules.SettingsManager.settings.AppleLocale ||
             NativeModules.SettingsManager.settings.AppleLanguages[0] ||
             'tg';
  } else {
    locale = NativeModules.I18nManager.localeIdentifier || 'tg';
  }
  
  // Extract language code (e.g., 'en-US' -> 'en')
  const languageCode = locale.split('-')[0];
  
  // Check if we support this language
  const supportedLanguages = ['en', 'ru', 'tg'];
  if (supportedLanguages.includes(languageCode)) {
    return languageCode;
  }
  
  return 'tg'; // Default to Tajik
};

// Set locale
I18n.locale = getDeviceLocale();

// Enable fallbacks
I18n.fallbacks = true;

export const supportedLanguages = [
  {code: 'en', name: 'English'},
  {code: 'ru', name: 'русский'},
  {code: 'tg', name: 'тоҷикӣ'},
];

export const setLanguage = (languageCode) => {
  I18n.locale = languageCode;
};

export const getCurrentLanguage = () => {
  return I18n.locale;
};

export const t = (key, options = {}) => {
  return I18n.t(key, options);
};

export default I18n;