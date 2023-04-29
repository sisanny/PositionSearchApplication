package com.sample.springapplicationpositionsearch.controller;

import com.sample.springapplicationpositionsearch.model.Position;
import com.sample.springapplicationpositionsearch.request.AddPositionRequest;
import com.sample.springapplicationpositionsearch.request.SearchPositionRequest;
import com.sample.springapplicationpositionsearch.service.ClientService;
import com.sample.springapplicationpositionsearch.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.List;

@Validated
@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private final PositionService positionService;
    @Autowired
    private final ClientService clientService;

    public PositionController(PositionService positionService, ClientService clientService) {
        this.positionService = positionService;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<URL> addPosition(@Valid @RequestBody(required = false) @NotNull(message = "RequestBody can not be null") AddPositionRequest positionRequest,
                                           @RequestHeader(required = false, value = "X-Api-Key") @NotBlank(message = "Api key can not be null") String apiKey) {

        clientService.validateApiKey(apiKey);
        Position newPosition = positionService.createPosition(positionRequest.getTitle(), positionRequest.getLocation());
        return ResponseEntity.ok().body(newPosition.getUrl());
    }


    @GetMapping("/search")
    public ResponseEntity<List<URL>> searchPositions(@Valid @RequestBody(required = false) @NotNull(message = "RequestBody can not be null") SearchPositionRequest positionRequest,
                                                     @RequestHeader(required = false, value = "X-Api-Key") @NotBlank(message = "Api key can not be null") String apiKey) {
        clientService.validateApiKey(apiKey);
        List<URL> positionUrls = positionService.searchPositions(positionRequest.getKeyword(), positionRequest.getLocation());
        return ResponseEntity.ok(positionUrls);
    }

    @GetMapping("/id")
    public ResponseEntity<Position> getPositionById(@RequestParam(required = false) @NotNull(message = "ID cannot be null") Long id,
                                                    @RequestHeader(required = false, value = "X-Api-Key") @NotBlank(message = "Api key can not be null") String apiKey) {

        clientService.validateApiKey(apiKey);
        Position position = positionService.getPositionById(id);
        return ResponseEntity.ok(position);
    }

}

