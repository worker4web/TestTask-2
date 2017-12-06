package ua.com.worker4web.uth.minigateway.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ua.com.worker4web.uth.minigateway.intf.Generator;
import ua.com.worker4web.uth.minigateway.model.AuthTransaction;

/**
 * Created by worker4web on 11/15/2017.
 */
@Component
public class GeneratorImpl implements Generator {

    public String generate(AuthTransaction authTransaction) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(authTransaction);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
