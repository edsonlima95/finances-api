package api.finances.repository;

import api.finances.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("from Category c where c.id = :id and c.user.id = :user_id")
    Optional<Category> findByUserId(Long id, Long user_id);

}
