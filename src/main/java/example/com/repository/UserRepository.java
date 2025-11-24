package example.com.repository;

import example.com.entity.User;
import example.com.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findAllByRoleInOrderById(Iterable<UserRole> role);
    Optional<User> findByEmailIgnoreCase (String email);

    @Modifying
    @Query("UPDATE User SET role = :role WHERE id = :id ")
    void updateRole(int id, @Param("role") UserRole newRole);
}
