package isthatkirill.vkproject.web.service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Kirill Emelyanov
 */

public interface RequestService {

    void saveRequest(HttpServletRequest request, boolean isAllowed, String username);

}
