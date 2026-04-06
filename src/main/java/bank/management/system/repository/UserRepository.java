package bank.management.system.repository;

import bank.management.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity operations
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByFormNo(String formNo);
    
    Optional<User> findByEmail(String email);
    
    boolean existsByFormNo(String formNo);
}
