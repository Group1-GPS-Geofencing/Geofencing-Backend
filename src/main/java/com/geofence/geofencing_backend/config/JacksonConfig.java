package com.geofence.geofencing_backend.config;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Jackson Config
 * Author: James Kalulu (Bsc-com-ne-21-19)
 * Created on: 19-04-2024
 * Last Modified on: 13-06-2024
 * Last Modified by: James Kalulu (Bsc-com-ne-21-19)
 */

@Configuration
public class JacksonConfig {

    @Bean
    //will provide a serializer/ deserializer for Geometry Types
    public JtsModule jtsModule(){
        return new JtsModule();
    }


    // another custom serializer/ deserializer for Geometry Types
    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.registerModule(jtsModule());
        return objectMapper;
    }
}
