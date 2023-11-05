package com.enzosagnelonge.adapters.postgre;

import com.enzosagnelonge.data.Movement;
import com.enzosagnelonge.ports.spi.MovementPersistencePort;
import com.enzosagnelonge.repository.postgre.PostgreSQLMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementPostgreAdapter implements MovementPersistencePort  {

    @Autowired
    private PostgreSQLMovementRepository movementRepository;

    @Override
    public Movement addMovement(Movement movement) {
        return movementRepository.addMovement(movement);
    }

    @Override
    public List<Movement> getLatestMovements(int limit) {
        return movementRepository.getLatestMovements(limit);
    }

    @Override
    public Boolean hasMovementInReferenceDeclared(String reference) {
        return movementRepository.hasMovementInReferenceDeclared(reference);
    }
}
