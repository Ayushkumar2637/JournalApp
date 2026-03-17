package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.ApiResponse.WeatherResponse;
import com.ayushkumar.journalApp.Services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {

    private static final String APIKEY = "d84d6432996351858756825c98c295a4";
    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherResponse weatherinfo(String city) {
        String finalApi = API.replace("API_KEY", APIKEY).replace("CITY", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        log.info(response.getStatusCode().toString());
        WeatherResponse weatherResponse = response.getBody();
        return weatherResponse;
    }

    /*

    Creating post call for understanding

    String val = "{\n" +
                "\t\"userName\":\"ram\",\n" +
                "    \"password\": \"ram\"\n" +
                "}";

    HttpHeaders httpHeaders=new HttpHeaders();
    httpHeaders.set("key","value");

    HttpEntity<String> httpEntity=new HttpEntity<>(val,httpHeaders);

    restTemplate.exchange(finalApi,HttpMethod.POST,httpEntity,WeatherResponse.class);

    */

}
