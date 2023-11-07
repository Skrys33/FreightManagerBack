package com.enzosagnelonge.services;


import com.enzosagnelonge.data.Goods;
import com.enzosagnelonge.ports.api.GoodsServicePort;
import com.enzosagnelonge.ports.spi.GoodsPersistencePort;
public class GoodsServiceImpl implements GoodsServicePort {
    private GoodsPersistencePort goodsPersistencePort;

    public GoodsServiceImpl(GoodsPersistencePort goodsPersistencePort) {
        this.goodsPersistencePort = goodsPersistencePort;
    }

    @Override
    public Long addGoods(Goods goods) {
        return goodsPersistencePort.addGoods(goods);
    }
}
