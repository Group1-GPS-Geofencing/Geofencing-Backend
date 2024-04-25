package com.geofence.geofencing_backend.config;

/*
 * Jackson Config
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 19-04-2024
 * Last Modified on: 25-04-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    //will provide a serializer/ deserializer for Geometry Types
    public JtsModule jtsModule(){
        return new JtsModule();
    }

}

