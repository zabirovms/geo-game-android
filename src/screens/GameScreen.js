import React, {useState, useEffect, useRef} from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  SafeAreaView,
  Alert,
  Dimensions,
} from 'react-native';
import MapView, {Marker, PROVIDER_GOOGLE} from 'react-native-maps';
import {useNavigation, useRoute} from '@react-navigation/native';
import {t} from '../services/localizationService';
import {getRandomCountry, getCountryByCode, countries} from '../data/countries';

const {width, height} = Dimensions.get('window');

const GameScreen = () => {
  const navigation = useNavigation();
  const route = useRoute();
  const {continent, mode} = route.params;
  
  const mapRef = useRef(null);
  const [currentCountry, setCurrentCountry] = useState(null);
  const [score, setScore] = useState(0);
  const [timeLeft, setTimeLeft] = useState(30);
  const [gameActive, setGameActive] = useState(true);
  const [selectedCountry, setSelectedCountry] = useState(null);

  useEffect(() => {
    startNewQuestion();
    startTimer();
  }, []);

  const startNewQuestion = () => {
    const [countryCode, country] = getRandomCountry(continent);
    setCurrentCountry({code: countryCode, ...country});
    setSelectedCountry(null);
    setTimeLeft(30);
    
    // Center map on the country
    if (mapRef.current && country.coordinates) {
      mapRef.current.animateToRegion({
        latitude: country.coordinates[0],
        longitude: country.coordinates[1],
        latitudeDelta: 10,
        longitudeDelta: 10,
      });
    }
  };

  const startTimer = () => {
    const timer = setInterval(() => {
      setTimeLeft((prev) => {
        if (prev <= 1) {
          clearInterval(timer);
          handleTimeUp();
          return 0;
        }
        return prev - 1;
      });
    }, 1000);
  };

  const handleTimeUp = () => {
    setGameActive(false);
    Alert.alert(
      t('game.timeUp'),
      `${t('game.finalScore')}: ${score}`,
      [
        {
          text: t('actions.restart'),
          onPress: () => {
            setScore(0);
            setGameActive(true);
            startNewQuestion();
            startTimer();
          },
        },
        {
          text: t('actions.close'),
          onPress: () => navigation.goBack(),
        },
      ]
    );
  };

  const handleMapPress = (event) => {
    if (!gameActive) return;
    
    const {latitude, longitude} = event.nativeEvent.coordinate;
    setSelectedCountry({latitude, longitude});
    
    // Check if the selected location is close to the target country
    if (currentCountry && currentCountry.coordinates) {
      const [targetLat, targetLng] = currentCountry.coordinates;
      const distance = calculateDistance(latitude, longitude, targetLat, targetLng);
      
      if (distance < 200) { // Within 200km
        setScore(score + 1);
        Alert.alert(t('game.correct'), '', [
          {text: t('actions.next'), onPress: startNewQuestion},
        ]);
      } else {
        Alert.alert(t('game.wrong'), '', [
          {text: t('actions.next'), onPress: startNewQuestion},
        ]);
      }
    }
  };

  const calculateDistance = (lat1, lon1, lat2, lon2) => {
    const R = 6371; // Earth's radius in kilometers
    const dLat = (lat2 - lat1) * (Math.PI / 180);
    const dLon = (lon2 - lon1) * (Math.PI / 180);
    const a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(lat1 * (Math.PI / 180)) *
        Math.cos(lat2 * (Math.PI / 180)) *
        Math.sin(dLon / 2) *
        Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R * c;
  };

  const getQuestionText = () => {
    if (!currentCountry) return '';
    
    switch (mode) {
      case 'countryName':
        return `${t('modes.countryName')}: ${currentCountry.name}`;
      case 'capital':
        return `${t('modes.capital')}: ${currentCountry.capital}`;
      case 'flag':
        return `${t('modes.flag')}: ${currentCountry.name}`;
      default:
        return currentCountry.name;
    }
  };

  return (
    <SafeAreaView style={styles.container}>
      {/* Header */}
      <View style={styles.header}>
        <View style={styles.headerLeft}>
          <Text style={styles.scoreText}>{t('game.score')}: {score}</Text>
          <Text style={styles.timeText}>{t('game.time')}: {timeLeft}s</Text>
        </View>
        <TouchableOpacity
          style={styles.closeButton}
          onPress={() => navigation.goBack()}>
          <Text style={styles.closeButtonText}>{t('actions.close')}</Text>
        </TouchableOpacity>
      </View>

      {/* Question */}
      <View style={styles.questionContainer}>
        <Text style={styles.questionText}>{getQuestionText()}</Text>
      </View>

      {/* Map */}
      <View style={styles.mapContainer}>
        <MapView
          ref={mapRef}
          style={styles.map}
          provider={PROVIDER_GOOGLE}
          initialRegion={{
            latitude: 20,
            longitude: 0,
            latitudeDelta: 50,
            longitudeDelta: 50,
          }}
          onPress={handleMapPress}>
          
          {/* Target country marker */}
          {currentCountry && currentCountry.coordinates && (
            <Marker
              coordinate={{
                latitude: currentCountry.coordinates[0],
                longitude: currentCountry.coordinates[1],
              }}
              title={currentCountry.name}
              description={currentCountry.capital}
              pinColor="red"
            />
          )}
          
          {/* Selected location marker */}
          {selectedCountry && (
            <Marker
              coordinate={selectedCountry}
              title={t('game.question')}
              pinColor="blue"
            />
          )}
        </MapView>
      </View>

      {/* Instructions */}
      <View style={styles.instructionsContainer}>
        <Text style={styles.instructionsText}>
          {t('gettingStarted.intro')}
        </Text>
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#ffffff',
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingVertical: 15,
    backgroundColor: '#f8f9fa',
    borderBottomWidth: 1,
    borderBottomColor: '#dee2e6',
  },
  headerLeft: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  scoreText: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#28a745',
    marginRight: 20,
  },
  timeText: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#dc3545',
  },
  closeButton: {
    paddingHorizontal: 15,
    paddingVertical: 8,
    backgroundColor: '#6c757d',
    borderRadius: 5,
  },
  closeButtonText: {
    color: '#ffffff',
    fontWeight: 'bold',
  },
  questionContainer: {
    padding: 20,
    backgroundColor: '#e9ecef',
    alignItems: 'center',
  },
  questionText: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
    textAlign: 'center',
  },
  mapContainer: {
    flex: 1,
    margin: 10,
    borderRadius: 10,
    overflow: 'hidden',
  },
  map: {
    flex: 1,
  },
  instructionsContainer: {
    padding: 15,
    backgroundColor: '#f8f9fa',
    borderTopWidth: 1,
    borderTopColor: '#dee2e6',
  },
  instructionsText: {
    fontSize: 14,
    color: '#666',
    textAlign: 'center',
    lineHeight: 20,
  },
});

export default GameScreen;
