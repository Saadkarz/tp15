package banque_service.demo.repositories;

import banque_service.demo.entities.Compte;
import banque_service.demo.entities.Transaction;
import banque_service.demo.entities.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    List<Transaction> findByCompte(Compte compte);
    
    @Query("SELECT SUM(t.montant) FROM Transaction t WHERE t.type = :type")
    double sumByType(@Param("type") TypeTransaction type);
}
