package com.enzosagnelonge.adapters.postgre;

import com.enzosagnelonge.data.Goods;
import com.enzosagnelonge.ports.spi.GoodsPersistencePort;
import com.enzosagnelonge.repository.postgre.PostgreSQLGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsPostgreAdapter implements GoodsPersistencePort {
    @Autowired
    private PostgreSQLGoodsRepository goodsRepository;

    @Override
    public Long addGoods(Goods goods) {
        return goodsRepository.addGoods(goods);
    }
}
