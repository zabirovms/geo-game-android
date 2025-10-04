import React, {useState} from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
  SafeAreaView,
  Alert,
} from 'react-native';
import {useNavigation} from '@react-navigation/native';
import {t, supportedLanguages, setLanguage, getCurrentLanguage} from '../services/localizationService';

const StartScreen = () => {
  const navigation = useNavigation();
  const [selectedLanguage, setSelectedLanguage] = useState(getCurrentLanguage());

  const handleLanguageChange = (languageCode) => {
    setLanguage(languageCode);
    setSelectedLanguage(languageCode);
  };

  const handlePlay = (continent, mode) => {
    navigation.navigate('Game', {continent, mode});
  };

  const showGettingStarted = () => {
    Alert.alert(
      t('gettingStarted.title'),
      t('gettingStarted.intro'),
      [{text: t('actions.close')}]
    );
  };

  return (
    <SafeAreaView style={styles.container}>
      <ScrollView contentContainerStyle={styles.scrollContent}>
        {/* Header */}
        <View style={styles.header}>
          <Text style={styles.title}>{t('header.title')}</Text>
          <Text style={styles.subtitle}>{t('header.subTitle')}</Text>
          <Text style={styles.description}>{t('header.description')}</Text>
        </View>

        {/* Language Selection */}
        <View style={styles.languageSection}>
          <Text style={styles.sectionTitle}>{t('languages.tg')}</Text>
          <View style={styles.languageButtons}>
            {supportedLanguages.map((lang) => (
              <TouchableOpacity
                key={lang.code}
                style={[
                  styles.languageButton,
                  selectedLanguage === lang.code && styles.selectedLanguageButton,
                ]}
                onPress={() => handleLanguageChange(lang.code)}>
                <Text
                  style={[
                    styles.languageButtonText,
                    selectedLanguage === lang.code && styles.selectedLanguageButtonText,
                  ]}>
                  {lang.name}
                </Text>
              </TouchableOpacity>
            ))}
          </View>
        </View>

        {/* Game Modes */}
        <View style={styles.modesSection}>
          <Text style={styles.sectionTitle}>{t('modes.countryName')}</Text>
          <View style={styles.modeButtons}>
            <TouchableOpacity
              style={styles.modeButton}
              onPress={() => handlePlay('asia', 'countryName')}>
              <Text style={styles.modeButtonText}>{t('modes.countryName')}</Text>
            </TouchableOpacity>
            <TouchableOpacity
              style={styles.modeButton}
              onPress={() => handlePlay('asia', 'capital')}>
              <Text style={styles.modeButtonText}>{t('modes.capital')}</Text>
            </TouchableOpacity>
            <TouchableOpacity
              style={styles.modeButton}
              onPress={() => handlePlay('asia', 'flag')}>
              <Text style={styles.modeButtonText}>{t('modes.flag')}</Text>
            </TouchableOpacity>
          </View>
        </View>

        {/* Continents */}
        <View style={styles.continentsSection}>
          <Text style={styles.sectionTitle}>{t('continents.asia')}</Text>
          <View style={styles.continentButtons}>
            {Object.entries({
              africa: t('continents.africa'),
              asia: t('continents.asia'),
              europe: t('continents.europe'),
              northAmerica: t('continents.northAmerica'),
              southAmerica: t('continents.southAmerica'),
              oceania: t('continents.oceania'),
            }).map(([continent, name]) => (
              <TouchableOpacity
                key={continent}
                style={styles.continentButton}
                onPress={() => handlePlay(continent, 'countryName')}>
                <Text style={styles.continentButtonText}>{name}</Text>
              </TouchableOpacity>
            ))}
          </View>
        </View>

        {/* Getting Started */}
        <TouchableOpacity style={styles.helpButton} onPress={showGettingStarted}>
          <Text style={styles.helpButtonText}>{t('gettingStarted.title')}</Text>
        </TouchableOpacity>
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#ffffff',
  },
  scrollContent: {
    padding: 20,
  },
  header: {
    alignItems: 'center',
    marginBottom: 30,
  },
  title: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#333',
    textAlign: 'center',
    marginBottom: 10,
  },
  subtitle: {
    fontSize: 16,
    color: '#666',
    textAlign: 'center',
    marginBottom: 10,
  },
  description: {
    fontSize: 14,
    color: '#888',
    textAlign: 'center',
    lineHeight: 20,
  },
  languageSection: {
    marginBottom: 30,
  },
  sectionTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 15,
  },
  languageButtons: {
    flexDirection: 'row',
    justifyContent: 'space-around',
  },
  languageButton: {
    paddingHorizontal: 20,
    paddingVertical: 10,
    borderRadius: 20,
    backgroundColor: '#f0f0f0',
    borderWidth: 2,
    borderColor: 'transparent',
  },
  selectedLanguageButton: {
    backgroundColor: '#007bff',
    borderColor: '#007bff',
  },
  languageButtonText: {
    fontSize: 16,
    color: '#333',
  },
  selectedLanguageButtonText: {
    color: '#ffffff',
  },
  modesSection: {
    marginBottom: 30,
  },
  modeButtons: {
    flexDirection: 'row',
    justifyContent: 'space-around',
  },
  modeButton: {
    paddingHorizontal: 15,
    paddingVertical: 12,
    borderRadius: 8,
    backgroundColor: '#28a745',
    minWidth: 80,
    alignItems: 'center',
  },
  modeButtonText: {
    fontSize: 14,
    color: '#ffffff',
    fontWeight: 'bold',
  },
  continentsSection: {
    marginBottom: 30,
  },
  continentButtons: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  continentButton: {
    width: '48%',
    paddingVertical: 15,
    paddingHorizontal: 10,
    borderRadius: 8,
    backgroundColor: '#6c757d',
    marginBottom: 10,
    alignItems: 'center',
  },
  continentButtonText: {
    fontSize: 16,
    color: '#ffffff',
    fontWeight: 'bold',
  },
  helpButton: {
    paddingVertical: 15,
    paddingHorizontal: 20,
    borderRadius: 8,
    backgroundColor: '#17a2b8',
    alignItems: 'center',
  },
  helpButtonText: {
    fontSize: 16,
    color: '#ffffff',
    fontWeight: 'bold',
  },
});

export default StartScreen;
