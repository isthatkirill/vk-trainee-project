package isthatkirill.vkproject.web.user.repository;

import isthatkirill.vkproject.web.user.model.AppUser;
import isthatkirill.vkproject.web.user.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kirill Emelyanov
 */

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void findAppUserByUsernameTest() {
        Optional<AppUser> appUser = appUserRepository.findAppUserByUsername("kirill");

        assertThat(appUser).isPresent()
                .get()
                .hasFieldOrPropertyWithValue("id", 2L)
                .hasFieldOrPropertyWithValue("username", "kirill")
                .hasFieldOrProperty("password");

        assertThat(appUser.get().getRoles())
                .hasSize(4)
                .containsExactlyInAnyOrder(Role.ROLE_USERS_EDITOR, Role.ROLE_POSTS_EDITOR,
                        Role.ROLE_USERS_VIEWER, Role.ROLE_POSTS_VIEWER);
    }

    @Test
    void findNonExistentAppUserByUsernameTest() {
        Optional<AppUser> appUser = appUserRepository.findAppUserByUsername("non-existent");

        assertThat(appUser).isNotPresent();
    }

    @Test
    void existsAppUserByUsernameTrueTest() {
        boolean isExists = appUserRepository.existsAppUserByUsername("anna");

        assertThat(isExists).isTrue();
    }

    @Test
    void existsAppUserByUsernameFalseTest() {
        boolean isExists = appUserRepository.existsAppUserByUsername("non-existent");

        assertThat(isExists).isFalse();
    }
}