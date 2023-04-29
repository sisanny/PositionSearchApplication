package com.sample.springapplicationpositionsearch.service;

import com.sample.springapplicationpositionsearch.exception.CustomException;
import com.sample.springapplicationpositionsearch.exception.FieldName;
import com.sample.springapplicationpositionsearch.model.Position;
import com.sample.springapplicationpositionsearch.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionService {
    public static final String BASIC_URL = "http://localhost:8080/position/%d";
    @Autowired
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position createPosition(String title, String location) {
        Position position = new Position(title, location);
        position = positionRepository.save(position);
        position.setUrl(BASIC_URL + position.getId());
        return positionRepository.save(position);
    }

    public List<URL> searchPositions(String title, String location) {
        List<Position> positions = positionRepository.findByTitleContainingIgnoreCaseAndLocationIgnoreCase(title, location);
        return positions.stream().map(Position::getUrl).collect(Collectors.toList());
    }

    public Position getPositionById(Long id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new CustomException("Position not found with this id: " + id, FieldName.ID));
    }
}

