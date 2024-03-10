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
@RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private final RequestClient requestClient;

    /**
     * Обработка GET-запросов к /posts/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /posts, /posts/1, /posts?userId=1, /posts/1/comments, /posts/1/comments?id=3
     */
    @GetMapping(value = {"", "/{postId}", "/{postId}/comments"})
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_POSTS_VIEWER')")
    public ResponseEntity<String> get(HttpServletRequest httpServletRequest,
                                     @RequestParam(required = false) Map<String, String> queryParams) {
        return ResponseEntity.ok(
                requestClient.get(buildURI(httpServletRequest, queryParams))
        );
    }

    /**
     * Обработка POST-запросов к /posts/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /posts, /posts/1/comments
     */
    @PostMapping(value = {"", "/{postId}/comments"})
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_POSTS_EDITOR')")
    public ResponseEntity<String> post(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                requestClient.post(buildURI(httpServletRequest), body)
        );
    }

    /**
     * Обработка PUT-запросов к /posts/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /posts/1
     */
    @PutMapping(value = "/{postId}")
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_POSTS_EDITOR')")
    public ResponseEntity<String> put(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return ResponseEntity.ok(
                requestClient.put(buildURI(httpServletRequest), body)
        );
    }

    /**
     * Обработка PATCH-запросов к /posts/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /posts/1
     */
    @PatchMapping(value = "/{postId}")
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_POSTS_EDITOR')")
    public ResponseEntity<String> patch(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return ResponseEntity.ok(
                requestClient.patch(buildURI(httpServletRequest), body)
        );
    }

    /**
     * Обработка DELETE-запросов к /posts/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /posts/1
     */
    @DeleteMapping(value = "/{postId}")
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_POSTS_EDITOR')")
    public ResponseEntity<Void> delete(HttpServletRequest httpServletRequest) {
        requestClient.delete(buildURI(httpServletRequest));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
