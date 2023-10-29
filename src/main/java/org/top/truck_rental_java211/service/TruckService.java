package org.top.truck_rental_java211.service;

import org.springframework.stereotype.Service;
import org.top.truck_rental_java211.entity.Company;
import org.top.truck_rental_java211.entity.Truck;

import java.util.Optional;

// Контракт сервиса для работы с грузовиками
@Service
public interface TruckService {

    //Базовые CRUD-операции
    Iterable<Truck> findAll();                   // получить все
    Optional<Truck> findByCompanyId(Integer companyId); // получить машины по id компании
    Optional<Truck> findById(Integer id);        // получить по id
    Optional<Truck> add(Integer companyId, Truck truck);    // добавить машину, определнной компании
    Optional<Truck> deleteById(Integer id);      // удалить по id
    Optional<Truck> updateById(Integer id, Truck truck);  //редактировать машину, в т.ч. закрыть

}
