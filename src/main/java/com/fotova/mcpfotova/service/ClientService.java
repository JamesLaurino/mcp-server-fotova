package com.fotova.mcpfotova.service;

import com.fotova.mcpfotova.dto.ClientDTO;
import com.fotova.mcpfotova.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> getAllClients() {
        return clientRepository.getAllClient();
    }
}
