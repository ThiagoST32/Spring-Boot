package cursospringboot.example.cursospringbootjava.repositories;

import cursospringboot.example.cursospringbootjava.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposetery extends JpaRepository<User, Long> {


}
