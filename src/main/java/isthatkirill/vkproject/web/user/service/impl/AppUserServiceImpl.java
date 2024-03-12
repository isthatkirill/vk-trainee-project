package isthatkirill.vkproject.web.user.service.impl;

import isthatkirill.vkproject.error.exception.EntityNotFoundException;
import isthatkirill.vkproject.error.exception.NotUniqueException;
import isthatkirill.vkproject.web.user.dto.AppUserDtoRequest;
import isthatkirill.vkproject.web.user.dto.AppUserDtoResponse;
import isthatkirill.vkproject.web.user.mapper.AppUserMapper;
import isthatkirill.vkproject.web.user.model.AppUser;
import isthatkirill.vkproject.web.user.model.Role;
import isthatkirill.vkproject.web.user.repository.AppUserRepository;
import isthatkirill.vkproject.web.user.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kirill Emelyanov
 */

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AppUserDtoResponse register(AppUserDtoRequest userDtoRequest) {
        checkIfUserExists(userDtoRequest.getUsername());
        AppUser appUser = userMapper.toAppUser(userDtoRequest);
        appUser.setRoles(List.of(Role.DEFAULT));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser = appUserRepository.save(appUser);
        return userMapper.toAppUserDtoResponse(appUser);
    }

    @Override
    @Transactional
    public AppUserDtoResponse changeUserRoles(Long userId, List<Role> roles) {
        AppUser appUser = checkIfUserExistsAndGet(userId);
        appUser.setRoles(roles);
        appUser = appUserRepository.save(appUser);
        return userMapper.toAppUserDtoResponse(appUser);
    }

    private void checkIfUserExists(String username) {
        if (appUserRepository.existsAppUserByUsername(username)) {
            throw new NotUniqueException("A user with the same name already exists");
        }
    }

    private AppUser checkIfUserExistsAndGet(Long userId) {
        return appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(AppUser.class, userId));
    }


}
