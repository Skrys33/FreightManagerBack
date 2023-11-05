package com.enzosagnelonge.configuration;

import com.enzosagnelonge.adapters.postgre.GoodsPostgreAdapter;
import com.enzosagnelonge.ports.api.GoodsServicePort;
import com.enzosagnelonge.ports.spi.GoodsPersistencePort;
import com.enzosagnelonge.service.GoodsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoodsConfig {
    @Bean
    public GoodsPersistencePort goodsPersistence(){
        return new GoodsPostgreAdapter();
    }

    @Bean
    public GoodsServicePort goodsService(){
        return new GoodsServiceImpl(goodsPersistence());
    }
}
