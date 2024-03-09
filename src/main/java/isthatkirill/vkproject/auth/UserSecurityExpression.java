package isthatkirill.vkproject.auth;

import isthatkirill.vkproject.api.service.RequestService;
import isthatkirill.vkproject.user.model.AppUser;
import isthatkirill.vkproject.user.model.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public boolean checkUsersAccessAndSave(HttpServletRequest httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAllowed = isHaveUsersRole(authentication) || isHaveAdminRole(authentication);
        String username = extractUsername(authentication);
        requestService.saveRequest(httpServletRequest, isAllowed, username);
        return isAllowed;
    }

    private String extractUsername(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }

    private boolean isHaveUsersRole(Authentication authentication) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Role.ROLE_USERS.name());
        return authentication.getAuthorities().contains(authority);
    }

    private boolean isHaveAdminRole(Authentication authentication) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Role.ROLE_ADMIN.name());
        return authentication.getAuthorities().contains(authority);
    }


}
