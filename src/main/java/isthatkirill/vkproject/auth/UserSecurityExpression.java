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
        boolean isAllowed = isHaveUsersRole(authentication) || isHaveAdminRole(authentication);
        String username = extractUsername(authentication);
        requestService.saveRequest(httpServletRequest, isAllowed, username);
        return isAllowed;
    }

    public boolean checkAccessToAlbumsAndSave(HttpServletRequest httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAllowed = isHaveAlbumsRole(authentication) || isHaveAdminRole(authentication);
        String username = extractUsername(authentication);
        requestService.saveRequest(httpServletRequest, isAllowed, username);
        return isAllowed;
    }

    private boolean isHaveAlbumsRole(Authentication authentication) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Role.ROLE_ALBUMS.name());
        return authentication.getAuthorities().contains(authority);
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
