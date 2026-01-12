package net.exploringspringboot.journalApp.Service;

import net.exploringspringboot.journalApp.api.respones.WeatherResoponse;
import net.exploringspringboot.journalApp.cache.AppCache;
import net.exploringspringboot.journalApp.constant.placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    private AppCache appCache;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RedisServices redisServices;


   public WeatherResoponse Getweather(String city){
       WeatherResoponse weatherResoponse = redisServices.get("weather_OF_" + city, WeatherResoponse.class);
       if(weatherResoponse != null) {
           return weatherResoponse;
       } else{
           String finalAPI = appCache.app_Cache.get("weather_api").replace(placeholders.City, city).replace(placeholders.API_KEY, apiKey);
           ResponseEntity<WeatherResoponse> respones = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResoponse.class);
           WeatherResoponse body = respones.getBody();
           if(body != null){
               redisServices.set( "weather_OF_"+ city ,body,300l);
           }
           return body;
       }


   }
}



