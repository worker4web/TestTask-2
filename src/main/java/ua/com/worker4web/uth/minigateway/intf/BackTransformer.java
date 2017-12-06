package ua.com.worker4web.uth.minigateway.intf;

import ua.com.worker4web.uth.minigateway.model.AuthTransaction;
import ua.com.worker4web.uth.minigateway.model.Transaction;

/**
 * Created by worker4web on 11/14/2017.
 */
public interface BackTransformer {
    void transform(Transaction transaction, AuthTransaction authTransaction);
}
