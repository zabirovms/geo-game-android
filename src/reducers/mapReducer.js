import {
  MAP_SET_VIEW,
  MAP_SET_ZOOM,
  MAP_SET_CENTER,
  MAP_SET_MARKER,
  MAP_REMOVE_MARKER,
} from '../actionTypes';

const initialState = {
  center: {
    latitude: 20,
    longitude: 0,
  },
  zoom: 2,
  markers: [],
  selectedMarker: null,
};

const mapReducer = (state = initialState, action) => {
  switch (action.type) {
    case MAP_SET_VIEW:
      return {
        ...state,
        center: action.payload.center,
        zoom: action.payload.zoom,
      };

    case MAP_SET_ZOOM:
      return {
        ...state,
        zoom: action.payload,
      };

    case MAP_SET_CENTER:
      return {
        ...state,
        center: action.payload,
      };

    case MAP_SET_MARKER:
      return {
        ...state,
        markers: [...state.markers, action.payload],
        selectedMarker: action.payload,
      };

    case MAP_REMOVE_MARKER:
      return {
        ...state,
        markers: state.markers.filter(marker => marker.id !== action.payload),
        selectedMarker: state.selectedMarker?.id === action.payload ? null : state.selectedMarker,
      };

    default:
      return state;
  }
};

export default mapReducer;