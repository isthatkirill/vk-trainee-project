package isthatkirill.vkproject.web.user.mapper;

import isthatkirill.vkproject.web.user.dto.AppUserDtoRequest;
import isthatkirill.vkproject.web.user.dto.AppUserDtoResponse;
import isthatkirill.vkproject.web.user.model.AppUser;
import isthatkirill.vkproject.web.user.model.Role;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kirill Emelyanov
 */

class AppUserMapperTest {

    private final AppUserMapper appUserMapper = Mappers.getMapper(AppUserMapper.class);

    @Test
    void toAppUserTest() {
        AppUserDtoRequest appUserDtoRequest = AppUserDtoRequest.builder()
                .username("username")
                .password("password")
                .build();

        AppUser appUser = appUserMapper.toAppUser(appUserDtoRequest);

        assertThat(appUser).isNotNull()
                .hasFieldOrPropertyWithValue("username", appUserDtoRequest.getUsername())
                .hasFieldOrPropertyWithValue("password", appUserDtoRequest.getPassword())
                .hasNoNullFieldsOrPropertiesExcept("id", "roles");
    }

    @Test
    void toAppUserDtoResponse() {
        AppUser appUser = AppUser.builder()
                .id(1L)
                .username("username")
                .password("password")
                .roles(List.of(Role.ROLE_USERS_EDITOR))
                .build();

        AppUserDtoResponse appUserDtoResponse = appUserMapper.toAppUserDtoResponse(appUser);

        assertThat(appUserDtoResponse).isNotNull()
                .hasFieldOrPropertyWithValue("id", appUser.getId())
                .hasFieldOrPropertyWithValue("roles", appUser.getRoles());
    }
}