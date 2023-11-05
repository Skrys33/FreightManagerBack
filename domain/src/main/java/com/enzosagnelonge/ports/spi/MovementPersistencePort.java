package com.enzosagnelonge.ports.spi;


import com.enzosagnelonge.data.Movement;

import java.util.List;

public interface MovementPersistencePort {
    Movement addMovement(Movement movement);
    List<Movement> getLatestMovements(int limit);
    Boolean hasMovementInReferenceDeclared(String reference);
}
