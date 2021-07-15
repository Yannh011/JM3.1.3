package web.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String roleName) {
        if (roleName.contains("ADMIN")) {
            this.id = 1L;
        } else if (roleName.contains("USER")) {
            this.id = 2L;
        }
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
