package bichla.league_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bichla.league_project.model.Summoner;
import java.util.List;
import java.util.Optional;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Repository
public interface SummonerRepository extends JpaRepository<Summoner, String> {
    @SuppressWarnings("null")
    Optional<Summoner> findById(String id);
    List<Summoner> findByAccountId(String accountId);
    Summoner findByPuuid(String puuid);
    List<Summoner> findByName(String name);

    @SuppressWarnings("null")
    @Override
    <S extends Summoner> S save(S entity);
}
