package hello.service;

import hello.aop.TrackTime;
import hello.dao.Dao1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Business1 {

    private Logger logger = LoggerFactory.getLogger(Business1.class);

    @Autowired
    private Dao1 dao1;

    @TrackTime
    public String calculate() {
        String value = dao1.retrieveData();

        logger.info("Business 1 {}", value);

        return value;
    }
}
