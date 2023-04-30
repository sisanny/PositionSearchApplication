package com.sample.springapplicationpositionsearch.controller;

import com.sample.springapplicationpositionsearch.exception.CustomError;
import com.sample.springapplicationpositionsearch.model.Position;
import com.sample.springapplicationpositionsearch.request.AddPositionRequest;
import com.sample.springapplicationpositionsearch.service.ClientService;
import com.sample.springapplicationpositionsearch.service.PositionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URL;
import java.util.List;

@Validated
@RestController
@RequestMapping("/position")
@Api(value = "Position", tags = {"Position"})
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
    @ApiOperation(value = "Add a new position", notes = "Returns the URL of the created position")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid request parameters", response = CustomError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = CustomError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = CustomError.class)
    })
    public ResponseEntity<URL> addPosition(@Valid @RequestBody(required = false) @NotNull(message = "RequestBody can not be null") AddPositionRequest positionRequest,
                                           @RequestHeader(required = false, value = "X-Api-Key") @NotBlank(message = "Api key can not be null") String apiKey) {

        clientService.validateApiKey(apiKey);
        Position newPosition = positionService.createPosition(positionRequest.getTitle(), positionRequest.getLocation());
        return ResponseEntity.ok().body(newPosition.getUrl());
    }


    @GetMapping("/search")
    @ApiOperation(value = "Search positions", notes = "Returns a list of URLs of the found positions")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid request parameters", response = CustomError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = CustomError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = CustomError.class)
    })
    public ResponseEntity<List<URL>> searchPositions(@RequestParam(required = false)
                                                     @NotBlank(message = "Keyword cannot be empty")
                                                     @Size(min = 3, max = 50, message = "Keyword must be more than 2 and less than 51 characters")
                                                     @ApiParam(value = "Search keyword", example = "Data Analyst")
                                                     String keyword,
                                                     @RequestParam(required = false)
                                                     @NotBlank(message = "Location cannot be empty")
                                                     @Size(max = 50, message = "Location must be less than 51 characters")
                                                     @ApiParam(value = "Search location", example = "London")
                                                     String location,
                                                     @RequestHeader(required = false, value = "X-Api-Key") @NotBlank(message = "Api key can not be null") String apiKey) {
        clientService.validateApiKey(apiKey);
        List<URL> positionUrls = positionService.searchPositions(keyword, location);
        return ResponseEntity.ok(positionUrls);
    }

    @GetMapping("/id")
    @ApiOperation(value = "Get position by ID", notes = "Returns the position with the given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid request parameters", response = CustomError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = CustomError.class),
            @ApiResponse(code = 404, message = "Position not found", response = CustomError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = CustomError.class)
    })
    public ResponseEntity<Position> getPositionById(@RequestParam(required = false) @NotNull(message = "ID cannot be null") Long id,
                                                    @RequestHeader(required = false, value = "X-Api-Key") @NotBlank(message = "Api key can not be null") String apiKey) {

        clientService.validateApiKey(apiKey);
        Position position = positionService.getPositionById(id);
        return ResponseEntity.ok(position);
    }

}

