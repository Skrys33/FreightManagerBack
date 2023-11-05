package com.enzosagnelonge.ports.api;

import com.enzosagnelonge.data.Movement;

import java.util.List;

public interface MovementServicePort {
    Movement addMovement(Movement movement);
    List<Movement> getLatestMovements(int limit);
}
