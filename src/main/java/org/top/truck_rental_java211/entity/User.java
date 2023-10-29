package org.top.truck_rental_java211.entity;

import jakarta.persistence.*;
import lombok.Data;

//сущность пользователь, соответствует таблице в БД
@Data
@Entity
@Table(name = "user_t")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="login_f", nullable = false)
    private String login;

    @Column(name="password_f", nullable = false)
    private String password;

    @Column(name = "role_f", nullable = false)
    private String role;


}
