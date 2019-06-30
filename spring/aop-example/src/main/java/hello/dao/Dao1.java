package hello.dao;

import org.springframework.stereotype.Component;

@Component
public class Dao1 {

    public String retrieveData() {
        return "Dao1";
    }
}
