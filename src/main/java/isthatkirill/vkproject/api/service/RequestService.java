package isthatkirill.vkproject.api.service;

import isthatkirill.vkproject.user.model.AppUser;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Kirill Emelyanov
 */

public interface RequestService {

    void saveRequest(HttpServletRequest request, boolean isAllowed, String username);

}
