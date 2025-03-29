package com.unicaes.poo.domain.user;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "Users")
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
    String email;
    String password;

}
