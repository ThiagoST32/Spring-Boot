package cursospringboot.example.cursospringbootjava.services;

import cursospringboot.example.cursospringbootjava.models.User;
import cursospringboot.example.cursospringbootjava.repositories.TaskRepository;
import cursospringboot.example.cursospringbootjava.repositories.UserReposetery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserReposetery userReposetery;

    @Autowired
    private TaskRepository taskRepository;


    public User findById(Long id) {
        Optional<User> user = this.userReposetery.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! id: " + id + ", tipo: " + User.class.getName()));
    }

    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = this.userReposetery.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;

    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userReposetery.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try{
            this.userReposetery.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Não é possivel excluir pos há entidades relacionadas");
        }
    }


}
