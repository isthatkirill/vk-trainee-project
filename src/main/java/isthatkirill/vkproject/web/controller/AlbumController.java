package isthatkirill.vkproject.web.controller;

import isthatkirill.vkproject.web.client.RequestClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class AlbumController {

    private final RequestClient requestClient;

    /**
     * Обработка GET-запросов к /albums/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /albums, /albums/1, /albums?userId=1, /albums/1/photos?id=7
     */
    @GetMapping(value = {"", "/{albumId}", "/{albumId}/photos"})
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_ALBUMS_VIEWER')")
    public ResponseEntity<String> get(HttpServletRequest httpServletRequest,
                                      @RequestParam(required = false) Map<String, String> queryParams) {
        return ResponseEntity.ok(
                requestClient.get(buildURI(httpServletRequest, queryParams))
        );
    }

    /**
     * Обработка POST-запросов к /albums/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /albums, /albums/1/photos
     */
    @PostMapping(value = {"", "/{albumId}/photos"})
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_ALBUMS_EDITOR')")
    public ResponseEntity<String> post(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                requestClient.post(buildURI(httpServletRequest), body)
        );
    }

    /**
     * Обработка PUT-запросов к /albums/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /albums/1
     */
    @PutMapping(value = "/{albumId}")
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_ALBUMS_EDITOR')")
    public ResponseEntity<String> put(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return ResponseEntity.ok(
                requestClient.put(buildURI(httpServletRequest), body)
        );
    }

    /**
     * Обработка PATCH-запросов к /albums/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /albums/1
     */
    @PatchMapping(value = "/{albumId}")
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_ALBUMS_EDITOR')")
    public ResponseEntity<String> patch(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return ResponseEntity.ok(
                requestClient.patch(buildURI(httpServletRequest), body)
        );
    }

    /**
     * Обработка DELETE-запросов к /albums/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /albums/1
     */
    @DeleteMapping(value = {"/{albumId}", "/{albumId}/photos/{photoId}"})
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_ALBUMS_EDITOR')")
    public ResponseEntity<Void> delete(HttpServletRequest httpServletRequest) {
        requestClient.delete(buildURI(httpServletRequest));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
