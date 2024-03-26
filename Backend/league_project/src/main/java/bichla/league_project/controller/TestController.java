package bichla.league_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bichla.league_project.exceptions.APIException;
import bichla.league_project.model.entity.LeagueEntry;
import bichla.league_project.model.entity.RiotAccount;
import bichla.league_project.model.entity.Summoner;
import bichla.league_project.service.APIService;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@RestController
public class TestController {

    private final APIService apiService;

    @Autowired
    private TestController(APIService apiService) {
        this.apiService = apiService;
    }

    /**
     * 
     * @param summonerName
     * @param tagLine
     * @param server
     * @param continent
     * @return
     */
    @RequestMapping("/api/rank/{continent}/{server}/{summonerName}/{tagLine}")
    public String getRank(@PathVariable("summonerName") final String summonerName,
                            @PathVariable("tagLine") final String tagLine,
                            @PathVariable("server") final String server,
                            @PathVariable("continent") final String continent) {

        try {
            RiotAccount riotAccount = apiService.sendAccountRequest(continent, summonerName, tagLine);
            Summoner summoner = apiService.sendSummonerRequest(server ,riotAccount.getPuuid());
            LeagueEntry leagueEntry = apiService.sendLeagueRequest(server, summoner.getId());
            return leagueEntry.getTier() + " " + leagueEntry.getRank() + " " + leagueEntry.getLeaguePoints() + " LP";
        } catch (APIException e) {
            e.printStackTrace();
            return null;
        }
    }
}
