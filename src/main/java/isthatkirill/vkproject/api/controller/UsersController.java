package isthatkirill.vkproject.api.controller;

import isthatkirill.vkproject.auth.UserSecurityExpression;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Kirill Emelyanov
 */

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserSecurityExpression userSecurityExpression;

    @Value("${json.placeholder.url}")
    private String url;

    @GetMapping({"/{userId}", ""})
    @PreAuthorize("@userSecurityExpression.checkUsersAccessAndSave(#httpServletRequest)")
    public RedirectView getUsers(HttpServletRequest httpServletRequest, @PathVariable (required = false) Long userId) {
        return new RedirectView(url + extractPath(httpServletRequest));
    }

    private String extractPath(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI().substring(4);
    }

}
