package com.enzosagnelonge.controller;

import com.enzosagnelonge.data.Movement;
import com.enzosagnelonge.ports.api.MovementServicePort;
import com.enzosagnelonge.services.GmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movement")
public class MovementController {

    @Autowired
    private MovementServicePort movementServicePort;

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public  ResponseEntity<String> addMovement(@RequestBody Movement movement){
        try {
            Movement createdMovement = movementServicePort.addMovement(movement);
            new GmailService().sendMail(createdMovement);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return  ResponseEntity.ok().build();
    }

    @GetMapping("/latest")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Movement> getLatestMovements(@RequestParam(name = "limit", defaultValue = "50") int limit){
        return movementServicePort.getLatestMovements(limit);
    }
}
