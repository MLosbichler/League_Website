package bichla.league_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bichla.league_project.model.RiotAccount;
import bichla.league_project.repository.RiotAccountRepository;

@Service
public class RiotAccountService {

    private final RiotAccountRepository riotAccountRepository;

    public RiotAccountService(final RiotAccountRepository riotAccountRepository) {
        this.riotAccountRepository = riotAccountRepository;
    }

    public List<RiotAccount> getAllAccounts() {
        return riotAccountRepository.findAll();
    }

    public Optional<RiotAccount> getAccountById(final Long id) {
        return riotAccountRepository.findById(id);
    }

    public List<RiotAccount> getAccountsByPuuid(final String puuid) {
        return riotAccountRepository.findByPuuid(puuid);
    }

    public List<RiotAccount> getAccountsByGameName(final String gameName, final String tagLine) {
        return riotAccountRepository.findByGameNameAndTagLine(gameName, tagLine);
    }

    public RiotAccount saveAccount(final RiotAccount account) {
        return riotAccountRepository.save(account);
    }

    @SuppressWarnings("null")
    public void deleteAccountById(final Long id) {
        riotAccountRepository.deleteById(id);
    }
}
