package isthatkirill.vkproject.api.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestClient {

    private final WebClient webClient;

    @Cacheable(value = "RequestClient::get", key = "#path")
    public String get(String path) {
        return webClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /*
        Кэширование остальных HTTP-методов небезопасно.

        Обновлять кэш с помощью @Caching и @CachePut не имеет смысла, т.к.
        https://jsonplaceholder.typicode.com не хранит наших изменений.

        Аналогично, использование @CacheEvict не имеет смысла по тем же причинам.
     */

    public String post(String path, Object body) {
        return webClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }



    public String put(String path, Object body) {
        return webClient.put()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String patch(String path, Object body) {
        return webClient.patch()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String delete(String path) {
        return webClient.delete()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
