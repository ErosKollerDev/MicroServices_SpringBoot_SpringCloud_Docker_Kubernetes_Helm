package com.eazybytes.accounts;

import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties({AccountsContactInfoDto.class})
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Accounts API", version = "1.0.0", contact =
@Contact(name = "Eros Koller", email = "eroskoller@gmail.com", url = "https://github.com/ErosKollerDev"),
        license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")

), externalDocs = @io.swagger.v3.oas.annotations.ExternalDocumentation(
        description = "MicroServices",
        url = "https://github.com/ErosKollerDev/MicroServices_SpringBoot_SpringCloud_Docker_Kubernetes_Helm.git"
),
        security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "basicAuth")
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
