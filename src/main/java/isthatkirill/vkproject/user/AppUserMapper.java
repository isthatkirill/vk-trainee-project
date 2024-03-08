package isthatkirill.vkproject.user;

import isthatkirill.vkproject.user.dto.AppUserDtoRequest;
import isthatkirill.vkproject.user.dto.AppUserDtoResponse;
import isthatkirill.vkproject.user.model.AppUser;
import org.mapstruct.Mapper;

/**
 * @author Kirill Emelyanov
 */

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUser toAppUser(AppUserDtoRequest appUserDtoRequest);

    AppUserDtoResponse toAppUserDtoResponse(AppUser appUser);

}
