package tech.luizmattos;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwmController {

    @Autowired
    private OwmSettings owmSettings;

    @GetMapping(path = "/api/v1/weather")
    public String printKey(@RequestParam int zipCode) throws APIException {
        OWM owm = new OWM(owmSettings.getApiKey());
        owm.setUnit(OWM.Unit.IMPERIAL);
        CurrentWeather currentWeather = owm.currentWeatherByZipCode(zipCode);
        return "It's currently " + currentWeather.getMainData().getTemp() + " degrees in " +currentWeather.getCityName();
    }

    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "Hello world from FriendlyForecast";
    }
}
