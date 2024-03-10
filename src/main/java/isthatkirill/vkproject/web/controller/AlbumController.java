package isthatkirill.vkproject.web.controller;

import isthatkirill.vkproject.web.client.RequestClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static isthatkirill.vkproject.util.PathBuilder.buildURI;

/**
 * @author Kirill Emelyanov
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/albums", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("@userSecurityExpression.checkAccessToAlbumsAndSave(#httpServletRequest)")
public class AlbumController {

    private final RequestClient requestClient;

    /**
     * Обработка GET-запросов к /albums/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /albums, /albums/1, /albums?userId=1, /albums/1/photos?id=7
     */
    @GetMapping(value = {"", "/{albumId}", "/{albumId}/photos"})
    public String get(HttpServletRequest httpServletRequest,
                      @RequestParam(required = false) Map<String, String> queryParams) {
        return requestClient.get(buildURI(httpServletRequest, queryParams));
    }

    /**
     * Обработка POST-запросов к /albums/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /albums, /albums/1/photos
     */
    @PostMapping(value = {"", "/{albumId}/photos"})
    public String post(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return requestClient.post(buildURI(httpServletRequest), body);
    }

    /**
     * Обработка PUT-запросов к /albums/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /albums/1
     */
    @PutMapping(value = "/{albumId}")
    public String put(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return requestClient.put(buildURI(httpServletRequest), body);
    }

    /**
     * Обработка PATCH-запросов к /albums/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /albums/1
     */
    @PatchMapping(value = "/{albumId}")
    public String patch(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return requestClient.patch(buildURI(httpServletRequest), body);
    }

    /**
     * Обработка DELETE-запросов к /albums/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /albums/1
     */
    @DeleteMapping(value = {"/{albumId}", "/{albumId}/photos/{photoId}"})
    public String delete(HttpServletRequest httpServletRequest) {
        return requestClient.delete(buildURI(httpServletRequest));
    }


}
