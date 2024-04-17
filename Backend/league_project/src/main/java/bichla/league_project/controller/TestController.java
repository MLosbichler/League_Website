package bichla.league_project.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bichla.league_project.model.dto.PlayerRankDTO;
import bichla.league_project.service.InternalAccountService;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@RestController
public class TestController {
    private final InternalAccountService accountService;

    private TestController(final InternalAccountService accountService) {
        this.accountService = accountService;
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
    public PlayerRankDTO getRank(@PathVariable("summonerName") final String summonerName,
                            @PathVariable("tagLine") final String tagLine,
                            @PathVariable("server") final String server,
                            @PathVariable("continent") final String continent) {
        return accountService.updateAccount(summonerName, tagLine, server, continent);
    }
}
