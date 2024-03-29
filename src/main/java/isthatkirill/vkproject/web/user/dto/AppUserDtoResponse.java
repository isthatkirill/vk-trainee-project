package isthatkirill.vkproject.web.user.dto;

import isthatkirill.vkproject.web.user.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Kirill Emelyanov
 */

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUserDtoResponse extends AppUserDtoRequest {

    Long id;
    List<Role> roles;

}
