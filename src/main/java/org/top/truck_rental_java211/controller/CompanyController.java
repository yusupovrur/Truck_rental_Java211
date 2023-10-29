package org.top.truck_rental_java211.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.truck_rental_java211.entity.Company;
import org.top.truck_rental_java211.service.CompanyService;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("")
    public String findAll(Model model) {
        Iterable<Company> companies = companyService.findAll();    // получить список компаний
        if (companies.iterator().hasNext()) {
            model.addAttribute("companies", companies);   // добавить их в контекст представления
        } else {
            model.addAttribute("companies", null);
        }
        return "company/company-list";                          // вернуть представление
    }

    // обработчик добавления компаний

    @GetMapping("new")
    public String addNew(Model model) {
        Company company = new Company(); //Объект для заполнения формы
        model.addAttribute("company", company);
        return "company/company-form";
    }

    @PostMapping("new")
    public String addNew(Company company, RedirectAttributes ra) {
        Optional<Company> newCompany = companyService.add(company);  //добавить компанию и перенаправиться на список машин
        if (newCompany.isPresent()) {
            ra.addAttribute("successMessage",
                    "Компания " + company.getName() + " успешно добавлена");
        } else {
            ra.addFlashAttribute("errorMessage",
                    "Компания с именем " + company.getName() + " уже существует");
        }
        return "redirect:/company";
    }

    //обработчик удаления
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        Optional<Company> removed = companyService.deleteById(id);
        if (removed.isPresent()) {
            ra.addFlashAttribute("successMessage",
                    removed.get().getName() + " удалена из списка компаний");
        } else {
            ra.addFlashAttribute("errorMessage",
                    " некорректный id");
        }
        return "redirect:/company";
    }

    // обработчик details
    @GetMapping("{id}")
    public String details(@PathVariable Integer id, Model model) {
        Optional<Company> company = companyService.findById(id);
        if (company.isPresent()) {
            model.addAttribute("company", company.get());
        } else {
            model.addAttribute("company", null);
        }
        return "company/company-details";
    }

    // обработчики обновления компании (редактирвоания компании)
    @GetMapping("update/{id}")
    public String updateExisting(@PathVariable Integer id, Model model) {
        Optional<Company> company = companyService.findById(id);    // объект для заполнения в форме
        if (company.isPresent()) {
            model.addAttribute("company", company.get());
        } else {
            model.addAttribute("company", null);
        }
        return "company/company-edit-form";
    }

    @PostMapping("update/{id}")
    public String updateExisting(@PathVariable Integer id, Company company, RedirectAttributes ra) {
        Optional<Company> updated = companyService.updateById(id, company);
        if (updated.isPresent()) {
            ra.addFlashAttribute("successMessage",
                    "Информация о компании успешно обновлена");
        } else {
            ra.addFlashAttribute("errorMessage",
                    "Информация о компании не была обновлена");
        }
        return "redirect:/company";     // перенаправиться на список компаний
    }

}