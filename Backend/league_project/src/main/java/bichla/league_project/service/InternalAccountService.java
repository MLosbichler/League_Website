package bichla.league_project.service;

import org.springframework.stereotype.Service;

import bichla.league_project.model.dto.PlayerRankDTO;
import bichla.league_project.model.entity.LeagueEntry;
import bichla.league_project.model.entity.RiotAccount;
import bichla.league_project.model.entity.Summoner;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Service
public class InternalAccountService {
    private final RiotAccountService riotAccountService;
    private final SummonerService summonerService;
    private final LeagueEntryService leagueEntryService;

    public InternalAccountService(final RiotAccountService riotAccountService,
                                    final SummonerService summonerService,
                                    final LeagueEntryService leagueEntryService) {
        this.riotAccountService = riotAccountService;
        this.summonerService = summonerService;
        this.leagueEntryService = leagueEntryService;
    }

    public PlayerRankDTO updateAccount(final String summonerName, final String tagLine,
                                        final String server, final String continent) {
        RiotAccount riotAccount = riotAccountService.saveRiotAccount(continent, summonerName, tagLine);
        Summoner summoner = summonerService.saveSummoner(server, riotAccount.getPuuid());
        LeagueEntry leagueEntry = leagueEntryService.saveLeagueEntry(server, summoner.getId()); 
        return createPlayerRankDTO(leagueEntry, riotAccount);
    }

    private PlayerRankDTO createPlayerRankDTO(final LeagueEntry leagueEntry, final RiotAccount riotAccount) {
        PlayerRankDTO playerRankDTO = new PlayerRankDTO();
        playerRankDTO.setSummonerName(riotAccount.getGameName());
        playerRankDTO.setTagLine(riotAccount.getTagLine());
        playerRankDTO.setTier(leagueEntry.getTier());
        playerRankDTO.setRank(leagueEntry.getRank());
        playerRankDTO.setLeaguePoints(leagueEntry.getLeaguePoints());
        playerRankDTO.setWins(leagueEntry.getWins());
        playerRankDTO.setLosses(leagueEntry.getLosses());
        return playerRankDTO;
    }
}
