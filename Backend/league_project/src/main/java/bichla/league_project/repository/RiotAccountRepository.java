package bichla.league_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bichla.league_project.model.RiotAccount;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Repository
public interface RiotAccountRepository extends JpaRepository<RiotAccount, String> {
    RiotAccount findByPuuid(String puuid);
    RiotAccount findByGameNameAndTagLine(String gameName, String tagLine);

    @SuppressWarnings("null")
    @Override
    <S extends RiotAccount> S save(S entity);
}
