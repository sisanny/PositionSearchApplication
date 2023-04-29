package com.sample.springapplicationpositionsearch.controller;

import com.sample.springapplicationpositionsearch.request.ClientRequest;
import com.sample.springapplicationpositionsearch.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<String> createClient(@Valid @RequestBody(required = false) @NotNull(message = "RequestBody can not be null") ClientRequest clientRequest) {
        clientService.checkIfEmailAlreadyExists(clientRequest.getEmail());
        UUID apiKey = clientService.createClient(clientRequest.getName(), clientRequest.getEmail()).getId();
        return ResponseEntity.ok(apiKey.toString());
    }
}
