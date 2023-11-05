package com.enzosagnelonge.adapters.mongodb;

import com.enzosagnelonge.data.Movement;
import com.enzosagnelonge.ports.spi.MovementPersistencePort;
import com.enzosagnelonge.repository.mongodb.SpringDataMongoMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Primary
public class MongoDbMovementAdapter implements MovementPersistencePort {
    @Autowired
    private SpringDataMongoMovementRepository orderRepository;

    @Override
    public Movement addMovement(Movement movement) {
        movement.setId(UUID.randomUUID());

        return orderRepository.save(movement);
    }

    /*  @Override
    public List<MovementDto> getLatestMovements(int limit) {

        Sort sort = Sort.by(Sort.Order.desc("creationDateTime"));
        Query query = new Query().with(sort).limit(limit);

        return mongoTemplate.find(query, MovementDto.class);
    } */

    @Override
    public List<Movement> getLatestMovements(int limit) {
        return orderRepository.findAll();//orderRepository.getLatestMovements(limit);
    }

    @Override
    public Boolean hasMovementInReferenceDeclared(String reference) {
        return orderRepository.hasMovementInReferenceDeclared(reference) != null;
    }
}
