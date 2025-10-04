import {
  PROFILE_LOAD,
  PROFILE_SAVE,
  PROFILE_UPDATE,
} from '../actionTypes';

const initialState = {
  id: null,
  name: '',
  language: 'tg',
  bestScores: {
    africa: 0,
    asia: 0,
    europe: 0,
    northAmerica: 0,
    southAmerica: 0,
    oceania: 0,
  },
  totalGamesPlayed: 0,
  totalTimePlayed: 0,
  achievements: [],
  settings: {
    soundEnabled: true,
    vibrationEnabled: true,
    showHints: true,
  },
};

const profileReducer = (state = initialState, action) => {
  switch (action.type) {
    case PROFILE_LOAD:
      return {
        ...state,
        ...action.payload,
      };

    case PROFILE_SAVE:
      return {
        ...state,
        ...action.payload,
      };

    case PROFILE_UPDATE:
      return {
        ...state,
        ...action.payload,
      };

    default:
      return state;
  }
};

export default profileReducer;