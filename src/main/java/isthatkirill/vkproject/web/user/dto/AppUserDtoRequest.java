package isthatkirill.vkproject.web.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

/**
 * @author Kirill Emelyanov
 */

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class AppUserDtoRequest {

    @Size(min = 5, max = 128, message = "Username must be from 5 to 128 characters")
    @NotBlank(message = "Username cannot be blank")
    String username;

    @Size(min = 5, max = 128, message = "Password must be from 5 to 128 characters")
    @NotBlank(message = "Password cannot be blank")
    String password;

}
