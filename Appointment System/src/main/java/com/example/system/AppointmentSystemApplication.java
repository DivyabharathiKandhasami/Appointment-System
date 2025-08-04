package com.example.system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.system.Entity.Role;
import com.example.system.Repository.RoleRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.system.Repository")
@EntityScan(basePackages = "com.example.system.Entity")

@OpenAPIDefinition(info = @Info(title = "Doctor Appointment API", version = "1.0", description = "API for managing doctor appointments, including search, creation, and management.", contact = @Contact(name = "Divyabharathi K", email = "divyakandhasami@gmail.com"), license = @License(name = "Apache 2.0", url = "http://springdoc.org")), servers = {
		@Server(url = "http://localhost:8080", description = "Local Server") })

public class AppointmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner initRoles(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("ROLE_USER") == null) {
				roleRepository.save(new Role("ROLE_USER"));
			}
			if (roleRepository.findByName("ROLE_ADMIN") == null) {
				roleRepository.save(new Role("ROLE_ADMIN"));
			}
		};
	}

}
