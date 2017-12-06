package ua.com.worker4web.uth.minigateway.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.worker4web.uth.minigateway.model.AuthTransaction;

/**
 * Created by worker4web on 11/15/2017.
 */
public interface AuthTransactionRepo extends JpaRepository<AuthTransaction, String> {

}
