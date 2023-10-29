package org.top.truck_rental_java211.rdb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.top.truck_rental_java211.entity.Company;
import org.top.truck_rental_java211.entity.Truck;
import org.top.truck_rental_java211.rdb.repository.TruckRepository;
import org.top.truck_rental_java211.service.CompanyService;
import org.top.truck_rental_java211.service.TruckService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RdbTruckService implements TruckService {

    private final TruckRepository truckRepository;
    private final CompanyService companyService;
    @Override
    public Iterable<Truck> findAll() {
        return truckRepository.findAll();
    }

    @Override
    public Optional<Truck> findByCompanyId(Integer companyId) {
        return Optional.empty();
    }

    @Override
    public Optional<Truck> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Truck> add(Integer companyId, Truck truck) {
        Optional<Company> company = companyService.findById(companyId);
        if (company.isEmpty()) {
            return Optional.empty(); //нельзя добавить машину
        }
        truck.setCompany(company.get());
        truck.setIsActive(true); //новая машина активна всегда
        return Optional.of(truckRepository.save(truck));
    }



    @Override
    public Optional<Truck> deleteById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Truck> updateById(Integer id, Truck truck) {
        return Optional.empty();
    }
}
