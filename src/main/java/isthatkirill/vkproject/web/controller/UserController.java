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
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("@userSecurityExpression.checkAccessToUsersAndSave(#httpServletRequest)")
public class UserController {

    private final RequestClient requestClient;

    /**
     * Обработка GET-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users, /users/1, /users?username=Bret, /users/1/todos, /users/1/albums?id=1
     */
    @GetMapping(value = {"", "/{userId}", "/{userId}/albums", "/{userId}/todos", "/{userId}/posts"})
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_USERS_VIEWER')")
    public ResponseEntity<String> get(HttpServletRequest httpServletRequest,
                                      @RequestParam(required = false) Map<String, String> queryParams) {
        return ResponseEntity.ok(
                requestClient.get(buildURI(httpServletRequest, queryParams))
        );
    }

    /**
     * Обработка POST-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users, /users/1/albums, /users/1/todos, /users/1/posts
     */
    @PostMapping(value = {"", "/{userId}/albums", "/{userId}/todos", "/{userId}/posts"})
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_USERS_EDITOR')")
    public ResponseEntity<String> post(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                requestClient.post(buildURI(httpServletRequest), body)
        );
    }

    /**
     * Обработка PUT-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users/1
     */
    @PutMapping(value = "/{userId}")
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_USERS_EDITOR')")
    public ResponseEntity<String> put(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return ResponseEntity.ok(
                requestClient.put(buildURI(httpServletRequest), body)
        );
    }

    /**
     * Обработка PATCH-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users/1
     */
    @PatchMapping(value = "/{userId}")
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_USERS_EDITOR')")
    public ResponseEntity<String> patch(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return ResponseEntity.ok(
                requestClient.patch(buildURI(httpServletRequest), body)
        );
    }

    /**
     * Обработка DELETE-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users/1
     */
    @DeleteMapping(value = "/{userId}")
    @PreAuthorize("@userSecurityExpression.checkPermissions(#httpServletRequest, 'ROLE_USERS_EDITOR')")
    public ResponseEntity<Void> delete(HttpServletRequest httpServletRequest) {
        requestClient.delete(buildURI(httpServletRequest));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
