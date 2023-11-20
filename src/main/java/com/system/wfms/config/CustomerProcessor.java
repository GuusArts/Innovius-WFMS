package com.system.wfms.config;

import com.system.wfms.Sensors;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Sensors, Sensors> {

    @Override
    public Sensors process(Sensors sensors) throws Exception {
        return sensors;
    }
}
