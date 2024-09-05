package cursospringboot.example.cursospringbootjava.services;

import cursospringboot.example.cursospringbootjava.models.User;
import cursospringboot.example.cursospringbootjava.repositories.UserReposetery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserReposetery userReposetery;



    public User findById(int id) {
        Optional<User> user = this.userReposetery.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! id: " + id + ", tipo: " + User.class.getName()));
    }

    public List<User> getAllUsers(){
        return this.userReposetery.findAll();
    }

    @Transactional
    public User create(User obj){
        obj.setId(0);
        obj = this.userReposetery.save(obj);
        return obj;

    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userReposetery.save(newObj);
    }

    public void delete(int id) {
        findById(id);
        try {
            this.userReposetery.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pos há entidades relacionadas");
        }
    }


}
