package bank.management.system.repository;

import bank.management.system.entity.AdditionalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for AdditionalDetails entity operations
 */
@Repository
public interface AdditionalDetailsRepository extends JpaRepository<AdditionalDetails, String> {

    Optional<AdditionalDetails> findByFormNo(String formNo);
}
