package nz.clem.book;

import nz.clem.book.role.Role;
import nz.clem.book.role.RoleRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class BookNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookNetworkApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRespository roleRespository) {
		return args -> {
			if(roleRespository.findByName("USER").isEmpty()) {
				roleRespository.save(
						Role.builder().name("USER").build()
				);
			}
		};
	}

}
