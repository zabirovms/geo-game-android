import {createStore, applyMiddleware, combineReducers} from 'redux';
import thunk from 'redux-thunk';
import logger from 'redux-logger';
import gameReducer from '../reducers/gameReducer';
import mapReducer from '../reducers/mapReducer';
import profileReducer from '../reducers/profileReducer';
import timerReducer from '../reducers/timerReducer';

const rootReducer = combineReducers({
  game: gameReducer,
  map: mapReducer,
  profile: profileReducer,
  timer: timerReducer,
});

const store = createStore(
  rootReducer,
  applyMiddleware(thunk, logger)
);

export {store};
