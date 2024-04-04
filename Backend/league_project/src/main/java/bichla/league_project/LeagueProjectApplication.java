package bichla.league_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import bichla.league_project.config.GlobalConfig;

@SpringBootApplication
@Import(GlobalConfig.class)
public class LeagueProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeagueProjectApplication.class, args);
	}

}
