package com.fotova.mcpfotova.tool;

import com.fotova.mcpfotova.dto.ClientDTO;
import com.fotova.mcpfotova.dto.DetailOrdersClientDTO;
import com.fotova.mcpfotova.service.ClientService;
import com.fotova.service.email.EmailService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientTools {

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    @Tool(name = "get_all_clients",
            description = "Get all clients information about the Fotova-creation e-commerce")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @Tool(name = "send_email_to_client",
            description = "Send an email to one or multiple clients using a map, " +
                    "where the email address is the key and the email content is the value.")
    public Map<String,String> sendEmailToClient(Map<String,String> emailContent, String content) {
        emailService.sendEmailMarketing(emailContent,content);
        return emailContent;
    }

    @Tool(
        name = "get_clients_order_summary",
        description = """
                    Returns a summary of all clients' purchasing activity.
                    For each client, provides: email address, total number of orders placed,
                    cumulative amount spent in euros, and the date of their most recent order.
                    Use this tool when you need to analyze customer purchase history,
                    identify top spenders, detect inactive clients, or get an overview
                    of a specific client's order behavior.
                """)
    public List<DetailOrdersClientDTO> getDetailOrdersClient() {
        return clientService.getDetailOrdersClient();
    }
}
