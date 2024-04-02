package bichla.league_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bichla.league_project.model.LeagueEntry;
import bichla.league_project.repository.LeagueEntryRepository;

@Service
public class LeagueEntryService {

    private final LeagueEntryRepository leagueEntryRepository;

    public LeagueEntryService(final LeagueEntryRepository leagueEntryRepository) {
        this.leagueEntryRepository = leagueEntryRepository;
    }

    // This exists for now only for debugging purposes.
    public List<LeagueEntry> getAllEntries() {
        return leagueEntryRepository.findAll();
    }

    public Optional<LeagueEntry> getEntryById(final Long id) {
        return leagueEntryRepository.findById(id);
    }

    public List<LeagueEntry> getEntriesBySummonerId(final String summonerId) {
        return leagueEntryRepository.findBySummonerId(summonerId);
    }

    public LeagueEntry saveEntry(final LeagueEntry leagueEntry) {
        return leagueEntryRepository.save(leagueEntry);
    }
}
