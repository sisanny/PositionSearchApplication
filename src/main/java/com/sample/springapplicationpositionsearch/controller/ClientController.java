package com.sample.springapplicationpositionsearch.controller;

import com.sample.springapplicationpositionsearch.exception.CustomError;
import com.sample.springapplicationpositionsearch.request.ClientRequest;
import com.sample.springapplicationpositionsearch.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Client", tags = {"Client"})
public class ClientController {
    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new client and returns api key", notes = "This endpoint creates a new client based on the given request.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client created successfully"),
            @ApiResponse(code = 400, message = "Bad request", response = CustomError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = CustomError.class)
    })
    public ResponseEntity<String> createClient(@Valid @RequestBody(required = false) @NotNull(message = "RequestBody can not be null") ClientRequest clientRequest) {
        clientService.checkIfEmailAlreadyExists(clientRequest.getEmail());
        UUID apiKey = clientService.createClient(clientRequest.getName(), clientRequest.getEmail()).getId();
        return ResponseEntity.ok(apiKey.toString());
    }
}
