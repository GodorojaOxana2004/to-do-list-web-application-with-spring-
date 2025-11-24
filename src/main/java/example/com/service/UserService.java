package example.com.service;

import example.com.entity.User;
import example.com.entity.UserRole;
import example.com.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> findById(int userId){
        return userRepository.findById(userId);

    }

    public void deleteUserById(int userId){
        userRepository.deleteById(userId);
    }

    public void updateRole(int id, UserRole newRole){
        userRepository.updateRole(id,newRole);
    }

    public List<User> findAllByRoleInOrderById(Iterable<UserRole> roles){
        return userRepository.findAllByRoleInOrderById(roles);
    }

    public User getUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.
                findByEmailIgnoreCase(email).
                orElseThrow(()->new IllegalArgumentException("User with email=" + email+"not found."));
    }
}
