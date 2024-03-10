package isthatkirill.vkproject.web.user.repository;

import isthatkirill.vkproject.web.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Kirill Emelyanov
 */

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByUsername(String username);

    boolean existsAppUserByUsername(String username);

}
