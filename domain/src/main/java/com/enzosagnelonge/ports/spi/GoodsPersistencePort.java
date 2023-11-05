package com.enzosagnelonge.ports.spi;


import com.enzosagnelonge.data.Goods;

public interface GoodsPersistencePort {
    Long addGoods(Goods goods);
}
