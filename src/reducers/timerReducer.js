import {
  TIMER_START,
  TIMER_STOP,
  TIMER_PAUSE,
  TIMER_RESUME,
  TIMER_TICK,
  TIMER_SET_DURATION,
} from '../actionTypes';

const initialState = {
  isRunning: false,
  isPaused: false,
  duration: 30,
  timeLeft: 30,
  startTime: null,
  pauseTime: null,
  totalPausedTime: 0,
};

const timerReducer = (state = initialState, action) => {
  switch (action.type) {
    case TIMER_START:
      return {
        ...state,
        isRunning: true,
        isPaused: false,
        timeLeft: state.duration,
        startTime: Date.now(),
        pauseTime: null,
        totalPausedTime: 0,
      };

    case TIMER_STOP:
      return {
        ...state,
        isRunning: false,
        isPaused: false,
        timeLeft: 0,
        startTime: null,
        pauseTime: null,
        totalPausedTime: 0,
      };

    case TIMER_PAUSE:
      return {
        ...state,
        isPaused: true,
        pauseTime: Date.now(),
      };

    case TIMER_RESUME:
      return {
        ...state,
        isPaused: false,
        totalPausedTime: state.totalPausedTime + (Date.now() - state.pauseTime),
        pauseTime: null,
      };

    case TIMER_TICK:
      return {
        ...state,
        timeLeft: Math.max(0, state.timeLeft - 1),
      };

    case TIMER_SET_DURATION:
      return {
        ...state,
        duration: action.payload,
        timeLeft: action.payload,
      };

    default:
      return state;
  }
};

export default timerReducer;