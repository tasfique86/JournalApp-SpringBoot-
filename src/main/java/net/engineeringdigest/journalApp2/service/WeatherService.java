package net.engineeringdigest.journalApp2.service;

import net.engineeringdigest.journalApp2.api.response.WeatherResponse;
import net.engineeringdigest.journalApp2.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private  String apiKey ;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AppCache appCache;

    @Autowired
    RedisService redisService;

    public WeatherResponse getWeather (String city) {
       WeatherResponse weatherResponse= redisService.get("weather_of_"+ city,WeatherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        }else {
            String finalAPI= appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace("<city>", city).replace("<apiKey>",apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body!=null){
                    redisService.set("weather_of_"+ city,body,300l);
            }
            return body;

        }

    }
}
