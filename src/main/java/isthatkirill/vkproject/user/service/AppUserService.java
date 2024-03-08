package isthatkirill.vkproject.user.service;

import isthatkirill.vkproject.user.dto.AppUserDtoRequest;
import isthatkirill.vkproject.user.dto.AppUserDtoResponse;
import isthatkirill.vkproject.user.model.Role;

import java.util.List;

/**
 * @author Kirill Emelyanov
 */

public interface AppUserService {

    AppUserDtoResponse register(AppUserDtoRequest userDtoRequest);

    AppUserDtoResponse changeUserRoles(Long userId, List<Role> roles);
}
