package cursospringboot.example.cursospringbootjava.services;

import cursospringboot.example.cursospringbootjava.models.User;
import cursospringboot.example.cursospringbootjava.models.task;
import cursospringboot.example.cursospringbootjava.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public task findById(Long id){
        Optional<task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada! Id: " + id + ", Tipo: "+ Task.class.getName()));
    }

    @Transactional
    public task create (task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;

    }

    @Transactional
    public task update(task obj){
        task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);

    }

    public void delete (Long id){
        findById(id);
        try {

            this.taskRepository.delete(findById(id));

        } catch (Exception e){

            throw new RuntimeException("Não pode ser deletado pois há entidades relacionadas!");


        }
    }

}
