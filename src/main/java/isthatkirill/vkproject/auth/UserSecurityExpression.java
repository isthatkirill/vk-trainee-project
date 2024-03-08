package isthatkirill.vkproject.auth;

import isthatkirill.vkproject.user.model.AppUser;
import isthatkirill.vkproject.user.model.Role;
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

@Slf4j
@Service("userSecurityExpression")
@RequiredArgsConstructor
public class UserSecurityExpression {

    public boolean canAccessUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return isHaveUsersRole(authentication);
    }

    private boolean isHaveUsersRole(Authentication authentication) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Role.ROLE_ADMIN.name());
        return authentication.getAuthorities().contains(authority);
    }


}
