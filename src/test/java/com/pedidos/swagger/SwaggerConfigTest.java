package com.pedidos.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SwaggerConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testOpenAPIBean() {
        OpenAPI openAPI = context.getBean(OpenAPI.class);
        assertNotNull(openAPI, "Bean do OpenAPI não pode ser nulo");
        assertNotNull(openAPI.getInfo(), "OpenAPI Info não deve ser nulo");
        assertNotNull(openAPI.getInfo().getTitle(), "Titulo do OpenAPI Info não deve ser nulo");
        assertNotNull(openAPI.getInfo().getVersion(), "Versão da OpenAPI Info não deve ser nulo");
    }
}