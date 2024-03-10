package isthatkirill.vkproject.web.repository;

import isthatkirill.vkproject.web.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kirill Emelyanov
 */

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
