package tech.luizmattos.controller;

import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.luizmattos.exception.BadRequestException;
import tech.luizmattos.exception.NotFoundException;
import tech.luizmattos.service.ForecastService;

@RestController
public class OwmController {

    @Autowired
    private ForecastService forecastService;

    @GetMapping(path = "/api/v1/weather")
    public Weather getWeatherByZipCode(@RequestParam String zipCode) throws BadRequestException {

        try {
            return forecastService.getWeatherByZipCode(zipCode);
        } catch (NullPointerException | NotFoundException e){
            System.out.println(e.getMessage()+e.getClass());
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(path = "/api/v1/weather/text")
    public String getWeatherTextByZipCode(@RequestParam String zipCode) throws BadRequestException {

        try {
            return forecastService.getWeatherByZipCode(zipCode).toString();
        } catch (NullPointerException | NotFoundException e){
            System.out.println(e.getMessage()+e.getClass());
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "Hello world from JupiterForecast";
    }
}
