package tech.luizmattos;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.luizmattos.exception.JupiterException;

@RestController
public class OwmController {

    @Autowired
    private OwmSettings owmSettings;

    @GetMapping(path = "/api/v1/weather")
    public String getWeatherByZipCode(@RequestParam int zipCode) throws JupiterException {
        OWM owm = new OWM(owmSettings.getApiKey());
        owm.setUnit(OWM.Unit.IMPERIAL);
        try {
            CurrentWeather currentWeather = owm.currentWeatherByZipCode(zipCode);
            return "It's currently " + currentWeather.getMainData().getTemp() + " degrees in " +currentWeather.getCityName();

        } catch (NullPointerException | APIException e){
            System.out.println(e.getMessage());
            throw new JupiterException("Sorry, something went wrong");
        }
    }

    @GetMapping(path = "/api/v1/json/weather")
    public CurrentWeather getWeatherObject(@RequestParam int zipCode) throws JupiterException {
        OWM owm = new OWM(owmSettings.getApiKey());
        owm.setUnit(OWM.Unit.IMPERIAL);
        try {
            return owm.currentWeatherByZipCode(zipCode);

        } catch (APIException e){
            System.out.println(e.getMessage());
            throw new JupiterException("Sorry, something went wrong");
        }
    }

    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "Hello world from JupiterForecast";
    }
}
