package bichla.league_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bichla.league_project.model.RiotAccount;
import java.util.List;
import java.util.Optional;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Repository
public interface RiotAccountRepository extends JpaRepository<RiotAccount, Long> {
    List<RiotAccount> findByPuuid(String puuid);
    List<RiotAccount> findByGameName(String gameName);

    @SuppressWarnings("null")
    Optional<RiotAccount> findById(Long id);

    @SuppressWarnings("null")
    @Override
    <S extends RiotAccount> S save(S entity);
}
