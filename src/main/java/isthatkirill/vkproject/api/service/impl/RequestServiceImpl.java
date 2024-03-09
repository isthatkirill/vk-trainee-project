package isthatkirill.vkproject.api.service.impl;

import isthatkirill.vkproject.api.model.Request;
import isthatkirill.vkproject.api.repository.RequestRepository;
import isthatkirill.vkproject.api.service.RequestService;
import isthatkirill.vkproject.error.exception.EntityNotFoundException;
import isthatkirill.vkproject.user.model.AppUser;
import isthatkirill.vkproject.user.repository.AppUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Kirill Emelyanov
 */


@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final AppUserRepository appUserRepository;
    private final RequestRepository requestRepository;

    @Override
    public void saveRequest(HttpServletRequest request, boolean isAllowed, String username) {
        requestRepository.save(buildRequest(request, isAllowed, username));
    }

    private Request buildRequest(HttpServletRequest httpRequest, boolean isAllowed, String username) {
        AppUser appUser = appUserRepository.findAppUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(AppUser.class, username));
        return Request.builder()
                .path(httpRequest.getRequestURI())
                .isAllowed(isAllowed)
                .creator(appUser)
                .build();
    }

}
