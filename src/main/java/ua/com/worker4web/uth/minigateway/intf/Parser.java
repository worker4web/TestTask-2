package ua.com.worker4web.uth.minigateway.intf;

import ua.com.worker4web.uth.minigateway.model.AuthTransaction;

/**
 * Created by worker4web on 11/15/2017.
 */
public interface Parser {

    void parse(AuthTransaction authTransaction, String responseContent);
}
