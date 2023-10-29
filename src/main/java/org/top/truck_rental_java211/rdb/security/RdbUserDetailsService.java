package org.top.truck_rental_java211.rdb.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.top.truck_rental_java211.entity.User;
import org.top.truck_rental_java211.rdb.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RdbUserDetailsService implements UserDetailsService {
    //источник данных
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       //Найти пользователя в базе
        Optional<User> user = userRepository.findUserByLogin(username);
        if (user.isEmpty()){
            return null;
        }
        RdbUserDetails userDetails = new RdbUserDetails();
        userDetails.setUser(user.get());
        return userDetails;
    }
}
