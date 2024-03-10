package isthatkirill.vkproject.web.user.mapper;

import isthatkirill.vkproject.web.user.dto.AppUserDtoRequest;
import isthatkirill.vkproject.web.user.dto.AppUserDtoResponse;
import isthatkirill.vkproject.web.user.model.AppUser;
import org.mapstruct.Mapper;

/**
 * @author Kirill Emelyanov
 */

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUser toAppUser(AppUserDtoRequest appUserDtoRequest);

    AppUserDtoResponse toAppUserDtoResponse(AppUser appUser);

}
