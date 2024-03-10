package isthatkirill.vkproject.auth;

import isthatkirill.vkproject.web.service.RequestService;
import isthatkirill.vkproject.web.user.model.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * @author Kirill Emelyanov
 */

@Service("userSecurityExpression")
@RequiredArgsConstructor
public class UserSecurityExpression {

    private final RequestService requestService;

    public boolean checkAccessToUsersAndSave(HttpServletRequest httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAllowed = isHaveGivenRole(authentication, Role.ROLE_USERS) || isHaveGivenRole(authentication, Role.ROLE_ADMIN);
        requestService.saveRequest(httpServletRequest, isAllowed, extractUsername(authentication));
        return isAllowed;
    }

    public boolean checkAccessToAlbumsAndSave(HttpServletRequest httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAllowed = isHaveGivenRole(authentication, Role.ROLE_ALBUMS) || isHaveGivenRole(authentication, Role.ROLE_ADMIN);
        requestService.saveRequest(httpServletRequest, isAllowed, extractUsername(authentication));
        return isAllowed;
    }

    public boolean checkAccessToPostsAndSave(HttpServletRequest httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAllowed = isHaveGivenRole(authentication, Role.ROLE_POSTS) || isHaveGivenRole(authentication, Role.ROLE_ADMIN);
        requestService.saveRequest(httpServletRequest, isAllowed, extractUsername(authentication));
        return isAllowed;
    }

    private boolean isHaveGivenRole(Authentication authentication, Role role) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return authentication.getAuthorities().contains(authority);
    }

    private String extractUsername(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }

}
