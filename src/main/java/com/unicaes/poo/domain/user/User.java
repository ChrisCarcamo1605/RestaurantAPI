package com.unicaes.poo.domain.user;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "User")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String username;
    @ManyToOne
    @JoinColumn(name = "type")
    UserType type;
    String email;
    String password;
    Boolean active;

}
