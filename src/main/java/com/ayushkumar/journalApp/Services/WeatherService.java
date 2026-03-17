package com.ayushkumar.journalApp.Services;

import com.ayushkumar.journalApp.ApiResponse.WeatherResponse;

public interface WeatherService {
    public WeatherResponse weatherinfo(String city);
}
