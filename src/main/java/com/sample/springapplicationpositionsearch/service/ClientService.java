package com.sample.springapplicationpositionsearch.service;

import com.sample.springapplicationpositionsearch.exception.CustomException;
import com.sample.springapplicationpositionsearch.exception.FieldName;
import com.sample.springapplicationpositionsearch.model.Client;
import com.sample.springapplicationpositionsearch.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class ClientService {
    private static final String API_KEY_REGEX_PATTERN = "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}";

    @Autowired
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(String name, String email) {
        Client client = Client.builder()
                .name(name)
                .email(email)
                .build();
        clientRepository.save(client);
        return client;
    }

    public void checkIfEmailAlreadyExists(String email) {
        if (clientRepository.findByEmail(email).isPresent()) {
            throw new CustomException("This email address is already registered: " + email, FieldName.EMAIL);
        }
    }

    public void validateApiKey(String apiKey) {
        Pattern pattern = Pattern.compile(API_KEY_REGEX_PATTERN);

        if (!pattern.matcher(apiKey).matches()) {
            throw new CustomException("This api key is in invalid format: " + apiKey, FieldName.API_KEY);
        } else if (clientRepository.findById(UUID.fromString(apiKey)).isEmpty()) {
            throw new CustomException("This api key is invalid: " + apiKey, FieldName.API_KEY);
        }
    }
}


