package isthatkirill.vkproject.api.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestClient {

    private final WebClient webClient;

    public Mono<Object> get(String path, Map<String, String> queryParams) {
        return webClient.get()
                .uri(buildURI(path, queryParams))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> post(String path, Object body) {
        return webClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> put(String path, Object body) {
        return webClient.put()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> patch(String path, Object body) {
        return webClient.patch()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Object> delete(String path) {
        return webClient.delete()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);
    }

    private MultiValueMap<String, String> toMultiValueMap(Map<String, String> queryParams) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.setAll(queryParams);
        return multiValueMap;
    }

    private String buildURI(String path, Map<String, String> queryParams) {
        return UriComponentsBuilder.fromPath(path)
                .queryParams(toMultiValueMap(queryParams))
                .build()
                .toString();
    }

}
