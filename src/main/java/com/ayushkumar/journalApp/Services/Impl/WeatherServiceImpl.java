package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.ApiResponse.WeatherResponse;
import com.ayushkumar.journalApp.AppCache.AppCache;
import com.ayushkumar.journalApp.Repository.RedisRepository;
import com.ayushkumar.journalApp.Services.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private AppCache appCache;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey ;

    @Autowired
    private RedisRepository redisRepository;

    @Override
    public WeatherResponse weatherinfo(String city) {
        WeatherResponse weatherResponse=redisRepository.get("weather-"+city,WeatherResponse.class);
        if(weatherResponse!=null){
            log.info("Response processed form Redis : "+weatherResponse);
        }
        else {
            String finalApi = appCache.urls.getOrDefault("weather-api",null).replace("<API_KEY>", apiKey).replace("<CITY>", city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            log.info(response.getStatusCode().toString());
            weatherResponse = response.getBody();
            boolean res=redisRepository.saveWithTTLInSec("weather-"+city,weatherResponse,10l);
            log.info("Value is saved in redis : "+res);
        }
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
