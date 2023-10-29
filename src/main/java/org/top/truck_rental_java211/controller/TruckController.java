package org.top.truck_rental_java211.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.truck_rental_java211.entity.Company;
import org.top.truck_rental_java211.entity.Truck;
import org.top.truck_rental_java211.service.CompanyService;
import org.top.truck_rental_java211.service.TruckService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("truck")
public class TruckController {

    //необходимые сервисы
    private final TruckService truckService;
    private final CompanyService companyService;

    //Получение списка всех машин
    @GetMapping("")
    public String findAll(Model model) {
        Iterable<Truck> trucks = truckService.findAll();    // получить список машин
        if (trucks.iterator().hasNext()) {
            model.addAttribute("trucks", trucks);   // добавить их в контекст представления
        } else {
            model.addAttribute("trucks", null);
        }
        return "truck/truck-list";                          // вернуть представление
    }

    //добавление новой машины
    @GetMapping("new")
    public String addNew(Model model) {
        Truck truck = new Truck();
        model.addAttribute("truck", truck);
        Iterable<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);
        return "truck/truck-form";
    }

    @GetMapping("new/{companyId}") //добавление для конкретной компании
    public String addNew(@PathVariable Integer companyId, Model model, RedirectAttributes ra) {
        Optional<Company> company = companyService.findById(companyId);
        if (company.isEmpty()) {
            ra.addFlashAttribute("errorMessage", "Не найдена компания");
            return "redirect:/company";
        }
        //подготовить все для отправления
        Truck truck = new Truck();
        model.addAttribute("truck", truck);
        Iterable<Company> companies = List.of(company.get());
        model.addAttribute("companies", companies);
        return "truck/truck-form";
    }

    @PostMapping("new")
    public String addNew(Truck truck, RedirectAttributes ra) {
        Optional<Truck> newTruck = truckService.add(truck.getCompany().getId(), truck);  //добавить машину и перенаправиться на список машин
        if (newTruck.isPresent()) {
            ra.addAttribute("successMessage",
                    "Машина " + truck.getTruckModel() + " успешно добавлена");
        } else {
            ra.addFlashAttribute("errorMessage",
                    "Такая машина " + truck.getTruckModel() + " уже существует");
        }
        return "redirect:/truck";
    }
}
