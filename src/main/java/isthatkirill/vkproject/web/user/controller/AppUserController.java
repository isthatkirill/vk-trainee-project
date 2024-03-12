package isthatkirill.vkproject.web.user.controller;

import isthatkirill.vkproject.web.user.dto.AppUserDtoRequest;
import isthatkirill.vkproject.web.user.dto.AppUserDtoResponse;
import isthatkirill.vkproject.web.user.model.Role;
import isthatkirill.vkproject.web.user.service.AppUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kirill Emelyanov
 */

@Validated
@RestController
@RequestMapping("/api/app")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    /**
     * Выдача ролей пользователю. Доступно только админу.
     */
    @PutMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AppUserDtoResponse> changeUserRoles(@RequestParam Long userId,
                                                              @RequestBody @NotNull List<Role> roles) {
        return ResponseEntity.ok(appUserService.changeUserRoles(userId, roles));
    }

    /**
     * Регистрация нового пользователя.
     */
    @PostMapping("/register")
    public ResponseEntity<AppUserDtoResponse> register(@RequestBody @Valid AppUserDtoRequest userDtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appUserService.register(userDtoRequest));
    }


}
