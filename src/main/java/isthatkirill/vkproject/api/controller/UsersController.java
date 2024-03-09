package isthatkirill.vkproject.api.controller;

import isthatkirill.vkproject.api.client.RequestClient;
import isthatkirill.vkproject.auth.UserSecurityExpression;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.view.RedirectView;
import reactor.core.publisher.Mono;

/**
 * @author Kirill Emelyanov
 */

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final RequestClient requestClient;

    @GetMapping(value = {"/{userId}", ""}, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public Mono<String> getUsers(HttpServletRequest httpServletRequest, @PathVariable (required = false) Long userId) {
        return requestClient.get(extractPath(httpServletRequest));
    }

    private String extractPath(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI().substring(4);
    }

}
