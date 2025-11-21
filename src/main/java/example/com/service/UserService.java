package example.com.service;

import example.com.entity.User;
import example.com.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user){
       userRepository.save(user);

    }

    public User getUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.
                findByEmailIgnoreCase(email).
                orElseThrow(()->new IllegalArgumentException("User with email=" + email+"not found."));
    }
}
