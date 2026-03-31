package com.fotova.mcpfotova.tool;

import com.fotova.mcpfotova.dto.ClientDTO;
import com.fotova.mcpfotova.service.ClientService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientTools {

    @Autowired
    private ClientService clientService;

    @Tool(name = "get_all_clients",
            description = "Get all clients information about the Fotova-creation e-commerce")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }
}
