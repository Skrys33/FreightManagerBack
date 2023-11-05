package com.enzosagnelonge.repository.postgre;

import com.enzosagnelonge.data.Goods;
import com.enzosagnelonge.ports.spi.GoodsPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class PostgreSQLGoodsRepository implements GoodsPersistencePort {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgreSQLGoodsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long addGoods(Goods goods) {
        String sql = "INSERT INTO goods (referenceType, reference, quantity, weight, quantityTotalReference, weightTotalReference, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();


        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, goods.getReferenceType());
            ps.setString(2, goods.getReference());
            ps.setInt(3, goods.getQuantity());
            ps.setDouble(4, goods.getWeight());
            ps.setInt(5, goods.getQuantityTotalReference());
            ps.setDouble(6, goods.getWeightTotalReference());
            ps.setString(7, goods.getDescription());

            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

}
