package org.top.truck_rental_java211.rdb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.top.truck_rental_java211.entity.Company;
import org.top.truck_rental_java211.rdb.repository.CompanyRepository;
import org.top.truck_rental_java211.service.CompanyService;


import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RdbCompanyService implements CompanyService {
    //инъекция зависимости через lombok
    private final CompanyRepository companyRepository;
    @Override
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Integer id) {
        return companyRepository.findById(id);
    }

    @Override
    public Optional<Company> add(Company company) {
        if (companyRepository.findByName(company.getName()).isPresent()) {
            return Optional.empty(); //есть такая компания
        }
        return Optional.of(companyRepository.save(company));
    }

    @Override
    public Optional<Company> deleteById(Integer id) {
       Optional<Company> removable = findById(id);
       if (removable.isPresent()) {
           companyRepository.deleteById(id);
       }
       return removable;  //вернем удаленный (empty если нет с таким id)
    }

    @Override
    public Optional<Company> updateById(Integer id, Company company) {
        Optional<Company> updated = findById(id);
        Optional<Company> duplicatedByNameCompany = companyRepository.findByName(company.getName());
        if (updated.isPresent() &&
                (duplicatedByNameCompany.isEmpty() ||
                        Objects.equals(duplicatedByNameCompany.get().getId(), id))) {
            company.setId(id);
            return Optional.of(companyRepository.save(company)); // сохраняем новые данные
        } else {
            return Optional.empty();   // вернем удаленный (empty если нет с таким id)
        }
    }
}
