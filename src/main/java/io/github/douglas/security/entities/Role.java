package io.github.douglas.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public enum Values {
        BASIC(1L), ADMIN(2L);

        Long roleId;

        Values(Long roleId) {
            this.roleId = roleId;
        }

        public Long getRoleId() {
            return roleId;
        }
    }
}
