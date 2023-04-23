package com.application.billingsystem.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Billing System Api",
                description = "Billing System", version = "1.0.0",
                contact = @Contact(
                        name = "Protchenko Kirill",
                        email = "kirill.protchenko@mail.ru",
                        url = "https://github.com/kirlozavr/BillingSystem"
                )
        )
)
public class OpenApiConfig {
}
