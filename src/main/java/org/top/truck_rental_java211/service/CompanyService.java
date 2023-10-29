package org.top.truck_rental_java211.service;

import org.springframework.stereotype.Service;
import org.top.truck_rental_java211.entity.Company;

import java.util.Iterator;
import java.util.Optional;

// Контракт сервиса для работы с компаниями
@Service
public interface CompanyService {

    //Базовые CRUD-операции
    Iterable<Company> findAll();                  // получить все
    Optional<Company> findById(Integer id);       // получить по id
    Optional<Company> add(Company company);                     // добавить
    Optional<Company> deleteById(Integer id);     // удалить по id
    Optional<Company> updateById(Integer id, Company company); // обновить по id

}
