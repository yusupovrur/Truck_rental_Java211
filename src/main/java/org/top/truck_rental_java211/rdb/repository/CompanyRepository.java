package org.top.truck_rental_java211.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.truck_rental_java211.entity.Company;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {

    Optional<Company> findByName (String name);


}
