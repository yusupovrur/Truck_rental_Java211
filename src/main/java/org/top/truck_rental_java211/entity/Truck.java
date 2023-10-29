package org.top.truck_rental_java211.entity;

//сущность грузовик, соответствующая таблице в БД

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "truck_t")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="truckModel_f", nullable = false)
    private String truckModel;

    @Column(name="capacity_f", nullable = false)
    private Integer capacity;

    @Column(name="rent_f", nullable = false)
    private String rent;

    @Column(name="is_active_f")
    private Boolean isActive;

    //связи

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    //багофикс

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return Objects.equals(id, truck.id) && Objects.equals(truckModel, truck.truckModel) && Objects.equals(capacity, truck.capacity) && Objects.equals(rent, truck.rent) && Objects.equals(isActive, truck.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, truckModel, capacity, rent, isActive);
    }
}
