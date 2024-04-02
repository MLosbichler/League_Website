package bichla.league_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bichla.league_project.model.Summoner;
import bichla.league_project.repository.SummonerRepository;

@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;

    public SummonerService(final SummonerRepository summonerRepository) {
        this.summonerRepository = summonerRepository;
    }

    public List<Summoner> getAllSummoners() {
        return summonerRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Summoner> getSummonerById(final Long id) {
        return summonerRepository.findById(id);
    }

    public List<Summoner> getSummonersByAccountId(final String accountId) {
        return summonerRepository.findByAccountId(accountId);
    }

    public List<Summoner> getSummonersByPuuid(final String puuid) {
        return summonerRepository.findByPuuid(puuid);
    }

    public List<Summoner> getSummonersBySummonerId(final Long summonerId) {
        return summonerRepository.findBySummonerId(summonerId);
    }

    public List<Summoner> getSummonersByName(final String name) {
        return summonerRepository.findByName(name);
    }

    public Summoner saveSummoner(final Summoner summoner) {
        return summonerRepository.save(summoner);
    }
}
