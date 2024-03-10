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
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public String get(HttpServletRequest httpServletRequest,
                      @RequestParam(required = false) Map<String, String> queryParams) {
        return requestClient.get(buildURI(httpServletRequest, queryParams));
    }

    /**
     * Обработка POST-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users, /users/1/albums, /users/1/todos, /users/1/posts
     */
    @PostMapping(value = {"", "/{userId}/albums", "/{userId}/todos", "/{userId}/posts"})
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public String post(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return requestClient.post(buildURI(httpServletRequest), body);
    }

    /**
     * Обработка PUT-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users/1
     */
    @PutMapping(value = {"/{userId}"})
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public String put(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return requestClient.put(buildURI(httpServletRequest), body);
    }

    /**
     * Обработка PATCH-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users/1
     */
    @PatchMapping(value = {"/{userId}"})
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public String patch(HttpServletRequest httpServletRequest, @RequestBody Object body) {
        return requestClient.patch(buildURI(httpServletRequest), body);
    }

    /**
     * Обработка DELETE-запросов к /users/**
     * Поддерживаются всевозможные запросы, представленные на jsonplaceholder.typicode.com/
     * Например: /users/1
     */
    @DeleteMapping(value = {"/{userId}"})
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public String delete(HttpServletRequest httpServletRequest) {
        return requestClient.delete(buildURI(httpServletRequest));
    }

}
