package ua.com.worker4web.uth.minigateway.intf;

import ua.com.worker4web.uth.minigateway.model.AuthTransaction;
import ua.com.worker4web.uth.minigateway.model.Transaction;

/**
 * Created by workwe4web on 11/15/2017.
 */
public interface Transformer {
    AuthTransaction transform(Transaction transaction);
}