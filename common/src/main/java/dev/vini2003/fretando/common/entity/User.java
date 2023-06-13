package dev.vini2003.fretando.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    public boolean isComplete() {
        return username != null && password != null;
    }

    @Override
    public String toString() {
        return "[ User ID: " + id +
                ", Username: " + username +
                ", Password: " + password +
                " ]";
    }
}
