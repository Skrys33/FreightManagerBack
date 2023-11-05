package com.enzosagnelonge.configuration;

import com.enzosagnelonge.adapters.postgre.MongoDbMovementAdapter;
import com.enzosagnelonge.ports.api.MovementServicePort;
import com.enzosagnelonge.ports.spi.MovementPersistencePort;
import com.enzosagnelonge.service.MovementServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovementConfig {
    @Bean
    public MovementPersistencePort movementPersistence(){
        return new MongoDbMovementAdapter();
    }

    @Bean
    public MovementServicePort movementService(){
        return new MovementServiceImpl(movementPersistence());
    }
}
