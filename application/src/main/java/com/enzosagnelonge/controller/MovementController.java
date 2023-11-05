package com.enzosagnelonge.controller;

import com.enzosagnelonge.data.Movement;
import com.enzosagnelonge.ports.api.MovementServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movement")
public class MovementController {

    @Autowired
    private MovementServicePort movementServicePort;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public  ResponseEntity<String> addMovement(@RequestBody Movement movement){
        try {
            movementServicePort.addMovement(movement);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); //TODO ?
        }

        return  ResponseEntity.ok().build();
    }

    @GetMapping("/latest")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Movement> getLatestMovements(@RequestParam(name = "limit", defaultValue = "50") int limit){
        return movementServicePort.getLatestMovements(limit);
    }
}
