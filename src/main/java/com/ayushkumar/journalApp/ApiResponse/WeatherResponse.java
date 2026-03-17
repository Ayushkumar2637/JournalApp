package com.ayushkumar.journalApp.ApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WeatherResponse {

    private Location location;
    private Current current;

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
        @JsonProperty("weather_descriptions")
        private ArrayList<String> weatherDescriptions;
        @JsonProperty("wind_speed")
        private int windSpeed;
        @JsonProperty("wind_degree")
        private int windDegree;
        @JsonProperty("wind_dir")
        private String windDir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        private int visibility;
        @JsonProperty("is_day")
        private String isDay;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public class Location{
        private String name;
        private String country;
        private String region;
        private String localtime;
    }

}
