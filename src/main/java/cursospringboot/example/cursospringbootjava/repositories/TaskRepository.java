package cursospringboot.example.cursospringbootjava.repositories;

import cursospringboot.example.cursospringbootjava.models.task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<task, Integer> {

//    List<task> findByUser_Id(Long id);

//    @Query(value = "SELECT t FROM task t WHERE t.user.id = :id")
//    List<task> findByUser_Id(@Param("id") InternalError id);

    @Query(value = "SELECT * FROM task t WHERE t.user_id = :id",nativeQuery = true)
    List<task> findByUser_Id(@Param("id") InternalError id);

}
