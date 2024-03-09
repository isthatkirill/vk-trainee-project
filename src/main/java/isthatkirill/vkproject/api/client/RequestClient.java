package isthatkirill.vkproject.api.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Kirill Emelyanov
 */

@Service
@RequiredArgsConstructor
public class RequestClient {

    private final WebClient webClient;

    public Mono<String> get(String path) {
        return webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(String.class);
    }


}
