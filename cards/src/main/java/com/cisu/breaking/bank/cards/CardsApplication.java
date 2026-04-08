package com.cisu.breaking.bank.cards;

import com.cisu.breaking.bank.cards.dto.CardsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication // main class spring-boot
//@ComponentScan({ComponentScan("com.cisu.breaking.bank.cards.controller")})
//@EnableJpaRepositories("com.cisu.breaking.bank.cards.repository")
//@EntityScan("com.cisu.breaking.bank.cards.entity")
@EnableConfigurationProperties(value = CardsContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") // activate the JPA JpaAuditing and leverage the BEAN with the name auditAwareImpl to understand the  current auditor via  "getCurrentAuditor"
@OpenAPIDefinition(
        info = @Info(
                title = "cards REST API Documentation",
                description = "REST API documentation for the cards microservice. It provides operations to manage customer details and their associated bank cards, including creation, retrieval, and deletion.",
                version = "V1",
                contact = @Contact(
                        name = "George Grekis",
                        email = "georgrekis@gmail.com",
                        url = "" // add my website when it's ready
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        )
//        externalDocs = @ExternalDocumentation(
//               description = "An externalDocs for more help",
//               url = "" // the url of  externalDocs
//        )
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}
