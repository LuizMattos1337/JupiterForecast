package tech.luizmattos;

import net.aksingh.owmjapis.api.APIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FriendlyForecastApplication {



    public static void main(String[] args) throws APIException {
        SpringApplication.run(FriendlyForecastApplication.class, args);

    }

}
