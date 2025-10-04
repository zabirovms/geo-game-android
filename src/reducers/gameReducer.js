import {
  GAME_START,
  GAME_END,
  GAME_PAUSE,
  GAME_RESUME,
  GAME_RESTART,
  GAME_SET_MODE,
  GAME_SET_AREA,
  GAME_ANSWER_CORRECT,
  GAME_ANSWER_WRONG,
  GAME_NEXT_QUESTION,
} from '../actionTypes';

const initialState = {
  isActive: false,
  isPaused: false,
  mode: 'countryName',
  area: 'asia',
  currentQuestion: null,
  score: 0,
  totalQuestions: 0,
  correctAnswers: 0,
  wrongAnswers: 0,
  startTime: null,
  endTime: null,
};

const gameReducer = (state = initialState, action) => {
  switch (action.type) {
    case GAME_START:
      return {
        ...state,
        isActive: true,
        isPaused: false,
        score: 0,
        totalQuestions: 0,
        correctAnswers: 0,
        wrongAnswers: 0,
        startTime: Date.now(),
        endTime: null,
      };

    case GAME_END:
      return {
        ...state,
        isActive: false,
        isPaused: false,
        endTime: Date.now(),
      };

    case GAME_PAUSE:
      return {
        ...state,
        isPaused: true,
      };

    case GAME_RESUME:
      return {
        ...state,
        isPaused: false,
      };

    case GAME_RESTART:
      return {
        ...initialState,
        mode: state.mode,
        area: state.area,
      };

    case GAME_SET_MODE:
      return {
        ...state,
        mode: action.payload,
      };

    case GAME_SET_AREA:
      return {
        ...state,
        area: action.payload,
      };

    case GAME_ANSWER_CORRECT:
      return {
        ...state,
        score: state.score + 1,
        correctAnswers: state.correctAnswers + 1,
        totalQuestions: state.totalQuestions + 1,
      };

    case GAME_ANSWER_WRONG:
      return {
        ...state,
        wrongAnswers: state.wrongAnswers + 1,
        totalQuestions: state.totalQuestions + 1,
      };

    case GAME_NEXT_QUESTION:
      return {
        ...state,
        currentQuestion: action.payload,
      };

    default:
      return state;
  }
};

export default gameReducer;