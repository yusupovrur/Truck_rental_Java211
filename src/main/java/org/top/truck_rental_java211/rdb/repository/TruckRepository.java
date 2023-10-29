package org.top.truck_rental_java211.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.truck_rental_java211.entity.Truck;

@Repository
public interface TruckRepository extends CrudRepository<Truck, Integer> {
}
