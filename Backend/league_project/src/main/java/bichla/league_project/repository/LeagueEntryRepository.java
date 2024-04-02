package bichla.league_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bichla.league_project.model.LeagueEntry;
import java.util.Optional;
import java.util.List;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Repository
public interface LeagueEntryRepository extends JpaRepository<LeagueEntry, Long>{
    @SuppressWarnings("null")
    Optional<LeagueEntry> findById(Long id);
    List<LeagueEntry> findBySummonerId(String summonerId);
    List<LeagueEntry> findByLeagueId(String leagueId);
    List<LeagueEntry> findBySummonerName(String summonerName);
    List<LeagueEntry> findByQueueType(String queueType);
    
    @SuppressWarnings("null")
    @Override
    <S extends LeagueEntry> S save(S entity);
}
