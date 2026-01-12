package net.exploringspringboot.journalApp.api.respones;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class WeatherResoponse{

    public Current current;


    @Getter
    @Setter
    public class Current{
//        @JsonProperty("observation_time")
//        private String ObservationTime;

        private int temperature;
        private  List<String>weatherDescripations;

        private int feelslike;

    }




}





