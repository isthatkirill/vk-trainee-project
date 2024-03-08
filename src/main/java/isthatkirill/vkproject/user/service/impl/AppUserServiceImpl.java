package isthatkirill.vkproject.user.service.impl;

import isthatkirill.vkproject.error.exception.EntityNotFoundException;
import isthatkirill.vkproject.error.exception.NotUniqueException;
import isthatkirill.vkproject.user.AppUserMapper;
import isthatkirill.vkproject.user.dto.AppUserDtoRequest;
import isthatkirill.vkproject.user.dto.AppUserDtoResponse;
import isthatkirill.vkproject.user.model.AppUser;
import isthatkirill.vkproject.user.model.Role;
import isthatkirill.vkproject.user.repository.AppUserRepository;
import isthatkirill.vkproject.user.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kirill Emelyanov
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper userMapper;

    @Override
    public AppUserDtoResponse register(AppUserDtoRequest userDtoRequest) {
        checkIfUserExists(userDtoRequest.getUsername());
        AppUser appUser = userMapper.toAppUser(userDtoRequest);
        appUser.setRoles(List.of(Role.DEFAULT));
        appUser = appUserRepository.save(appUser);
        return userMapper.toAppUserDtoResponse(appUser);
    }

    @Override
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
