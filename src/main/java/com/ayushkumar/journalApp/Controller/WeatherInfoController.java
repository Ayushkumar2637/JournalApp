package com.ayushkumar.journalApp.Controller;

import com.ayushkumar.journalApp.ApiResponse.WeatherResponse;
import com.ayushkumar.journalApp.Services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherInfoController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponse > getWeatherInfo(@PathVariable("city") String city){
        WeatherResponse weatherResponse=weatherService.weatherinfo(city);
        return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
    }

}
