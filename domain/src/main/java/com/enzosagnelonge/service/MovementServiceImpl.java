package com.enzosagnelonge.service;


import com.enzosagnelonge.data.Movement;
import com.enzosagnelonge.ports.api.MovementServicePort;
import com.enzosagnelonge.ports.spi.MovementPersistencePort;

import java.util.List;

public class MovementServiceImpl implements MovementServicePort {
    private MovementPersistencePort movementPersistencePort;

    public MovementServiceImpl(MovementPersistencePort movementPersistencePort) {
        this.movementPersistencePort = movementPersistencePort;
    }

    @Override
    public Movement addMovement(Movement movement) {
        if (
            !movement.getGoods().isReferenceValid() ||
            !movement.getGoods().isQuantityValid() ||
            !movement.getGoods().isWeightValid()) {
            throw new RuntimeException("Invalid Movement");
        }
        return movementPersistencePort.addMovement(movement);
    }

    @Override
    public List<Movement> getLatestMovements(int limit) {
        return movementPersistencePort.getLatestMovements(limit);
    }
}
