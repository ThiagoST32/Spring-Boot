package cursospringboot.example.cursospringbootjava.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table (name = task.TABLE_NAME)
public class task {

    public static final String TABLE_NAME = "task";
    @Id
    @Column (name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;





    @Column(name = "description", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size (min = 1, max = 255)
    private String description;

    public task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        task other = (task) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
            else if (!this.id.equals(other.id)) {
                return false;
            }
        }
        return Objects.equals(this.id, other.id) && Objects.equals(this.user, other.user) && Objects.equals(this.description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, description);
    }
}
