package bichla.league_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bichla.league_project.model.entity.LeagueEntry;
import java.util.List;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Repository
public interface LeagueEntryRepository extends JpaRepository<LeagueEntry, String>{
    LeagueEntry findBySummonerId(String summonerId);
    List<LeagueEntry> findByLeagueId(String leagueId);
    
    @SuppressWarnings("null")
    @Override
    <S extends LeagueEntry> S save(S entity);
}
