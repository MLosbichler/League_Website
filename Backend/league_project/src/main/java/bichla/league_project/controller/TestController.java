package bichla.league_project.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bichla.league_project.model.LeagueEntry;
import bichla.league_project.model.RiotAccount;
import bichla.league_project.model.Summoner;
import bichla.league_project.service.LeagueEntryService;
import bichla.league_project.service.RiotAccountService;
import bichla.league_project.service.SummonerService;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@RestController
public class TestController {
    private final RiotAccountService riotAccountService;
    private final SummonerService summonerService;
    private final LeagueEntryService leagueEntryService;

    private TestController(final RiotAccountService riotAccountService,
                            final SummonerService summonerService,
                            final LeagueEntryService leagueEntryService) {
        this.riotAccountService = riotAccountService;
        this.summonerService = summonerService;
        this.leagueEntryService = leagueEntryService;
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
        RiotAccount riotAccount = riotAccountService.saveRiotAccount(continent, summonerName, tagLine);
        Summoner summoner = summonerService.saveSummoner(server, riotAccount.getPuuid());
        LeagueEntry leagueEntry = leagueEntryService.saveLeagueEntry(server, summoner.getId());
        return leagueEntry.getTier() + " " + leagueEntry.getRank() + " " + leagueEntry.getLeaguePoints() + " LP";
    }
}
