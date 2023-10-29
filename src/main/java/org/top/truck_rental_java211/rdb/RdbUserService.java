package org.top.truck_rental_java211.rdb;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.top.truck_rental_java211.entity.User;
import org.top.truck_rental_java211.rdb.repository.UserRepository;
import org.top.truck_rental_java211.service.UserService;

@Service
@RequiredArgsConstructor
public class RdbUserService implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean register(String login, String password, String passwordConfirmation) {
        if (!password.equals(passwordConfirmation) || userRepository.findUserByLogin(login).isPresent()) {
            return false;   // пароли не совпали или пользователь с таким логином уже существует
        }
        User user = new User();
        user.setLogin(login);
        user.setRole("USER");   // по умолчанию роль - USER
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return true;
    }
}