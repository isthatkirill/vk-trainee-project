package isthatkirill.vkproject.api.controller;

import isthatkirill.vkproject.api.client.RequestClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author Kirill Emelyanov
 */

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final RequestClient requestClient;

    /**
     * Обработка GET-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users, /users/1, /users?username=Bret, /users/1/todos, /users/1/albums?id=1
     */
    @GetMapping(value = {"", "/{userId}", "/{userId}/albums", "/{userId}/todos", "/{userId}/posts"})
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public Mono<Object> get(HttpServletRequest httpServletRequest,
                            @RequestParam(required = false) Map<String, String> queryParams) {
        return requestClient.get(extractPath(httpServletRequest), queryParams);
    }

    /**
     * Обработка POST-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users, /users/1/albums, /users/1/todos, /users/1/posts
     */
    @PostMapping(value = {"", "/{userId}/albums", "/{userId}/todos", "/{userId}/posts"})
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public Mono<Object> post(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return requestClient.post(extractPath(httpServletRequest), body);
    }

    /**
     * Обработка PUT-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users/1
     */
    @PutMapping(value = {"/{userId}"})
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public Mono<Object> put(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return requestClient.put(extractPath(httpServletRequest), body);
    }

    /**
     * Обработка PATCH-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users/1
     */
    @PatchMapping(value = {"/{userId}"})
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public Mono<Object> patch(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return requestClient.patch(extractPath(httpServletRequest), body);
    }

    /**
     * Обработка DELETE-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users/1
     */
    @DeleteMapping(value = {"/{userId}"})
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public Mono<Object> delete(HttpServletRequest httpServletRequest) {
        return requestClient.delete(extractPath(httpServletRequest));
    }

    private String extractPath(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI().substring(4);
    }



}
