package isthatkirill.vkproject.auth;

import isthatkirill.vkproject.web.service.impl.RequestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Kirill Emelyanov
 */

@SpringBootTest
class UserSecurityExpressionTest {

    @Autowired
    private UserSecurityExpression userSecurityExpression;

    @MockBean
    private RequestServiceImpl requestService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void checkPermissionsAdminTest() {
        boolean isAllowed = userSecurityExpression
                .checkPermissions(new MockHttpServletRequest(), "ROLE_USERS_VIEWER");

        assertThat(isAllowed).isTrue();
    }

    @Test
    @WithMockUser(roles = "USERS_VIEWER")
    void checkPermissionsAnotherRoleTest() {
        boolean isAllowed = userSecurityExpression
                .checkPermissions(new MockHttpServletRequest(), "ROLE_USERS_VIEWER");

        assertThat(isAllowed).isTrue();
    }

    @Test
    @WithMockUser(roles = "USERS_VIEWER")
    void checkPermissionsIsFailedTest() {
        boolean isAllowed = userSecurityExpression
                .checkPermissions(new MockHttpServletRequest(), "ROLE_POSTS_EDITOR");

        assertThat(isAllowed).isFalse();
    }

}