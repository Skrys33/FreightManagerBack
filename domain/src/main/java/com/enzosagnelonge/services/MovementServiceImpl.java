package com.enzosagnelonge.services;


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
        if (!movement.getGoods().isReferenceValid()) throw new RuntimeException("Invalid Goods Reference - The reference must consist of exactly 11 digits when the goods type is AWB.");
        if (!movement.getGoods().isQuantityValid()) throw new RuntimeException("Invalid Goods Quantity - The quantity of the reference must be greater than or equal to the quantity of the goods in the movement.");
        if (!movement.getGoods().isWeightValid()) throw new RuntimeException("Invalid Goods Weight - The total weight of the reference must be greater than or equal to the total weight of the goods in the movement.");
        if (movement.getTypeMovement().equals(Movement.TypeMovement.Out) && !movementPersistencePort.hasMovementInReferenceDeclared(movement.getGoods().getReference())) throw new RuntimeException("Invalid Goods Reference - You cannot declare an exit for a goods reference for which an entry has not been declared.");

        return movementPersistencePort.addMovement(movement);
    }

    @Override
    public List<Movement> getLatestMovements(int limit) {
        return movementPersistencePort.getLatestMovements(limit);
    }

}
