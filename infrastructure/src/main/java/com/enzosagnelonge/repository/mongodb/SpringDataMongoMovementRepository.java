package com.enzosagnelonge.repository.mongodb;

import com.enzosagnelonge.data.Movement;
import com.enzosagnelonge.entity.MovementEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataMongoMovementRepository extends MongoRepository<Movement, UUID> {
    @Query("{'typeMovement': 'In', 'goods.reference': ?0}")
    List<Movement> hasMovementInReferenceDeclared(String reference);

    @Aggregation(pipeline = {
            "{ '$sort' : { 'movementDateTime' : -1 } }",
            "{ '$limit' : ?0 }"
    })
    List<Movement> getLatestMovements(int limit);
}