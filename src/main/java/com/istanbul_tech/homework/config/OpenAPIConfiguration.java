package com.istanbul_tech.homework.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Local server");

        Contact myContact = new Contact();
        myContact.setName("ismail kattan");
        myContact.setEmail("ismailkattan.contact@gmail.com");
        myContact.setExtensions(
                Map.of(
                    "linkedin", "https://www.linkedin.com/in/ismail-kattan",
                    "github", "https://github.com/IsmailKattan",
                    "phone", "+905314326118"
                )
        );

        Info myInfo = new Info()
                .title("API For Managing User Addresses")
                .description("This is a simple API app built as a homework for Istanbul Tech API Development and integrating Bootcamp.")
                .version("1.0.0")
                .contact(myContact);

        return new OpenAPI()
                .info(myInfo)
                .servers(List.of(server));
    }

}
