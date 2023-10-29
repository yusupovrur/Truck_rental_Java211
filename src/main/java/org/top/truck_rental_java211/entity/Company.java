package org.top.truck_rental_java211.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

//сущность Компания, соответствующая таблице в БД
@Data
@Entity
@Table(name = "company_t")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;

    @Column(name="website_f", nullable = false)
    private String website;

    @Column(name="email_f", nullable = false)
    private String email;

    @Column(name ="phone_f", nullable = false)
    private String phone;


    //связи

    @OneToMany(mappedBy = "company")
    private Set<Truck> trucks;


}
