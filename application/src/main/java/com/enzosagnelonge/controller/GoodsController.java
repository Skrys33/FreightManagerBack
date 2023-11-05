package com.enzosagnelonge.controller;


import com.enzosagnelonge.data.Goods;
import com.enzosagnelonge.ports.api.GoodsServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsServicePort goodsServicePort;
    @PostMapping("/add")
    public Long addGoods(@RequestBody Goods goods){
        return goodsServicePort.addGoods(goods);
    }
}
