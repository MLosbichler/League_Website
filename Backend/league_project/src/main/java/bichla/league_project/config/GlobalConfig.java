package bichla.league_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Configuration
public class GlobalConfig {

    @Bean
    public String apiKey() {
        return "";
    }

    @Bean
    public String http() {
        return "https://";
    }

    @Bean
    public String accountApiName() {
        return ".api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
    }

    @Bean
    public String accountApiPuuid() {
        return ".api.riotgames.com/riot/account/v1/accounts/by-puuid/";
    }

    @Bean
    public String summonerApi() {
        return ".api.riotgames.com/lol/summoner/v4/summoners/by-puuid/";
    }

    @Bean
    public String leagueApi() {
        return ".api.riotgames.com/lol/league/v4/entries/by-summoner/";
    }

    @Bean
    public String discordClientId() {
        return "1225456729110745128";
    }

    @Bean
    public String discordClientSecret() {
        return "dzBo6mwLgKQsWYfd_vtaToAZ-0q2F7_U";
    }
}
