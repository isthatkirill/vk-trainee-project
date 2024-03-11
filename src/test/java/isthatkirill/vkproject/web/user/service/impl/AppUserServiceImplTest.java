package isthatkirill.vkproject.web.user.service.impl;

import isthatkirill.vkproject.error.exception.EntityNotFoundException;
import isthatkirill.vkproject.error.exception.NotUniqueException;
import isthatkirill.vkproject.web.user.dto.AppUserDtoRequest;
import isthatkirill.vkproject.web.user.dto.AppUserDtoResponse;
import isthatkirill.vkproject.web.user.model.Role;
import isthatkirill.vkproject.web.user.service.AppUserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Kirill Emelyanov
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase
class AppUserServiceImplTest {

    @Autowired
    private AppUserService appUserService;

    @Test
    @Order(1)
    void registerTest() {
        AppUserDtoRequest newUser = AppUserDtoRequest.builder()
                .username("new_username")
                .password("new_password")
                .build();

        AppUserDtoResponse persistentUser = appUserService.register(newUser);

        assertThat(persistentUser)
                .hasFieldOrPropertyWithValue("id", 4L);

        assertThat(persistentUser.getRoles()).hasSize(1)
                .containsExactlyInAnyOrder(Role.DEFAULT);
    }

    @Test
    @Order(2)
    void registerSameUserTest() {
        AppUserDtoRequest sameUser = AppUserDtoRequest.builder()
                .username("new_username")
                .password("new_password")
                .build();

        assertThrows(NotUniqueException.class,
                () -> appUserService.register(sameUser));
    }

    @Test
    @Order(3)
    void changeUserRolesTest() {
        List<Role> roles = new ArrayList<>(List.of(Role.ROLE_USERS_EDITOR));

        AppUserDtoResponse appUser = appUserService.changeUserRoles(4L, roles);

        assertThat(appUser.getRoles()).hasSize(1)
                .containsExactlyInAnyOrder(Role.ROLE_USERS_EDITOR);
    }

    @Test
    @Order(4)
    void changeNonExistentUserRolesTest() {
        List<Role> roles = List.of(Role.ROLE_USERS_EDITOR, Role.ROLE_POSTS_EDITOR);

        assertThrows(EntityNotFoundException.class,
                () -> appUserService.changeUserRoles(999L, roles));
    }

}