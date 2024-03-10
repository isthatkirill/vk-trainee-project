package isthatkirill.vkproject.web.user.service;

import isthatkirill.vkproject.web.user.dto.AppUserDtoRequest;
import isthatkirill.vkproject.web.user.dto.AppUserDtoResponse;
import isthatkirill.vkproject.web.user.model.Role;

import java.util.List;

/**
 * @author Kirill Emelyanov
 */

public interface AppUserService {

    AppUserDtoResponse register(AppUserDtoRequest userDtoRequest);

    AppUserDtoResponse changeUserRoles(Long userId, List<Role> roles);
}
