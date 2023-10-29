package org.top.truck_rental_java211.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.truck_rental_java211.service.UserService;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public String register(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String passwordConfirmation,
            RedirectAttributes ra
    ) {
        if (userService.register(login, password, passwordConfirmation)) {
            ra.addFlashAttribute("successMessage", "Успешно зарегистрирован. Попробуйте войти.");
        } else {
            ra.addFlashAttribute("errorMessage", "Ошибка регистрации");
        }
        return "redirect:/login";
    }
}