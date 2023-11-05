package com.enzosagnelonge.repository.postgre;

import com.enzosagnelonge.data.Movement;
import com.enzosagnelonge.ports.spi.MovementPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PostgreSQLMovementRepository implements MovementPersistencePort {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgreSQLMovementRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Movement addMovement(Movement movement) {
        String sql = "INSERT INTO movement (creationDateTime, creationUser, movementDateTime, location, goodsId, labelWarehouse, codeWarehouse, customsStatus, referenceAuthorization, typeAuthorization, typeMovement) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sql,
                movement.getCreationDateTime(),
                movement.getCreationUser(),
                movement.getMovementDateTime(),
                movement.getLocation(),
                movement.getGoods().getId(),
                movement.getLabelWarehouse(),
                movement.getCodeWarehouse(),
                movement.getCustomsStatus(),
                movement.getReferenceAuthorization(),
                movement.getTypeAuthorization(),
                movement.getTypeMovement().toString()
        );

        return null;
    }

    @Override
    public List<Movement> getLatestMovements(int limit) {

        String sql = "SELECT * FROM movement ORDER BY movementdatetime DESC LIMIT ?";
        List<Movement> movements = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Movement.class),
        limit);

    System.out.println("OKKKK" + movements.size() + " - " + movements.get(0).toString());
        return movements;
    }

    @Override
    public Boolean hasMovementInReferenceDeclared(String reference) {
        return null;
    }

}
