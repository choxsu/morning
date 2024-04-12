export interface WeatherVo {
  location?: Location;
  daily?: Daily[];
  now?: Now;
  alarm?: [];
  lastUpdate?: string;
}

export interface Now {
  precipitation?: number;
  temperature?: number;
  pressure?: number;
  humidity?: number;
  windDirection?: string;
  windDirectionDegree?: number;
  windSpeed?: number;
  windScale?: string;
}

export interface Daily {
  date?: string;
  high?: number;
  dayText?: string;
  dayCode?: number;
  dayWindDirection?: string;
  dayWindScale?: string;
  low?: number;
  nightText?: string;
  nightCode?: number;
  nightWindDirection?: string;
  nightWindScale?: string;
}

export interface Location {
  id?: string;
  name?: string;
  path?: string;
  longitude?: number;
  latitude?: number;
  timezone?: number;
}
