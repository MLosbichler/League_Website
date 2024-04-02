package bichla.league_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "bichla.league_project.repository")
public class LeagueProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeagueProjectApplication.class, args);
	}

}
