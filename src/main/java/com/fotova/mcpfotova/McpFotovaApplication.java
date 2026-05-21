package com.fotova.mcpfotova;

import com.fotova.mcpfotova.tool.ClientTools;
import com.fotova.mcpfotova.tool.SalesTools;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.fotova.mcpfotova",
        "com.fotova.service.email"
})
public class McpFotovaApplication {

    @Autowired
    private SalesTools salesTools;

    @Autowired
    private ClientTools clientTools;

    public static void main(String[] args) {
        SpringApplication.run(McpFotovaApplication.class, args);
    }

    @Bean
    public MethodToolCallbackProvider getMethodToolCallBackProvider() {
        /* TODO : put this method in a conf file */
        return MethodToolCallbackProvider.builder()
                .toolObjects(salesTools,clientTools)
                .build();
    }
}
