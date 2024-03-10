package isthatkirill.vkproject.web.service.impl;

import isthatkirill.vkproject.error.exception.EntityNotFoundException;
import isthatkirill.vkproject.web.model.Request;
import isthatkirill.vkproject.web.repository.RequestRepository;
import isthatkirill.vkproject.web.service.RequestService;
import isthatkirill.vkproject.web.user.model.AppUser;
import isthatkirill.vkproject.web.user.repository.AppUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kirill Emelyanov
 */


@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final AppUserRepository appUserRepository;
    private final RequestRepository requestRepository;

    @Override
    @Transactional
    public void saveRequest(HttpServletRequest request, boolean isAllowed, String username) {
        requestRepository.save(buildRequest(request, isAllowed, username));
    }

    private Request buildRequest(HttpServletRequest httpRequest, boolean isAllowed, String username) {
        AppUser appUser = appUserRepository.findAppUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(AppUser.class, username));
        return Request.builder()
                .path(httpRequest.getRequestURI())
                .isAllowed(isAllowed)
                .method(httpRequest.getMethod())
                .creator(appUser)
                .build();
    }

}
