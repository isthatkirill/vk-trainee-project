package isthatkirill.vkproject.api.repository;

import isthatkirill.vkproject.api.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kirill Emelyanov
 */

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
