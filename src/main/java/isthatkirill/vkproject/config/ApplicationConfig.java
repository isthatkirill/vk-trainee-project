package isthatkirill.vkproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Kirill Emelyanov
 */

@Configuration
public class ApplicationConfig {

    @Value("${json.placeholder.url}")
    private String url;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(url).build();
    }

}
