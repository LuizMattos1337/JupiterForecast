package tech.luizmattos.service;

import com.github.prominence.openweathermap.api.model.weather.Weather;
import tech.luizmattos.exception.BadRequestException;
import tech.luizmattos.exception.NotFoundException;

public interface ForecastService {
    Weather getWeatherByZipCode(String zipCode) throws BadRequestException, NotFoundException;
}
