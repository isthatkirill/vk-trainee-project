package isthatkirill.vkproject.api.model;

import isthatkirill.vkproject.user.model.AppUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * @author Kirill Emelyanov
 */

@Getter
@Setter
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreationTimestamp
    LocalDateTime sentAt;

    @ManyToOne
    @JoinColumn(name = "app_user_id", referencedColumnName = "id")
    AppUser creator;

    String path;
    boolean isAllowed;

}