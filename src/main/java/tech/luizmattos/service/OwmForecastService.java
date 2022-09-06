package tech.luizmattos.service;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import lombok.Data;
import org.springframework.stereotype.Service;
import tech.luizmattos.config.OwmSettings;
import tech.luizmattos.exception.BadRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Service
public class OwmForecastService implements ForecastService{

    private OwmSettings owmSettings;
    private OpenWeatherMapClient owmClient;

    @Override
    public Weather getWeatherByZipCode(String zipCode) throws BadRequestException {
        if (!isValidZip(zipCode)){
            throw new BadRequestException("You've entered an invalid zip code. Please try again.");
        }
        Weather weather;
        try {
            weather = owmClient
                    .currentWeather()
                    .single()
                    .byZipCodeInUSA(zipCode)
                    .unitSystem(UnitSystem.IMPERIAL)
                    .retrieve()
                    .asJava();
            return weather;
        } catch (NoDataFoundException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public OwmForecastService(OwmSettings owmSettings) {
        this.owmSettings = owmSettings;
        this.owmClient = new OpenWeatherMapClient(owmSettings.getApiKey());
    }

    private boolean isValidZip(String zipCode){
        final String regexForValidZip = "^[0-9]{5}(?:-[0-9]{4})?$";
        Pattern pattern = Pattern.compile(regexForValidZip);
        Matcher matcher = pattern.matcher(zipCode);
        return matcher.matches();
    }
}