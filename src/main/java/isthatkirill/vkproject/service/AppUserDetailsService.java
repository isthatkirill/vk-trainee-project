package isthatkirill.vkproject.service;

import isthatkirill.vkproject.model.AppUser;
import isthatkirill.vkproject.model.Role;
import isthatkirill.vkproject.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        log.error("{} {} {}", appUser.getPassword(), appUser.getUsername(), appUser.getRoles());

        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                mapAuthorities(appUser.getRoles())
        );
    }

    private List<SimpleGrantedAuthority> mapAuthorities(List<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

}
