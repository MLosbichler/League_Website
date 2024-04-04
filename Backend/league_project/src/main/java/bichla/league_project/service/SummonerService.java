package bichla.league_project.service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import bichla.league_project.exceptions.APIException;
import bichla.league_project.model.Summoner;
import bichla.league_project.repository.SummonerRepository;
import bichla.league_project.util.HttpRequestBuilder;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;
    private final HttpRequestBuilder httpRequestBuilder;
    private final String http;
    private final String summonerApi;

    public SummonerService(final SummonerRepository summonerRepository,
                            final HttpRequestBuilder httpRequestBuilder,
                            final String http,
                            final String summonerApi) {
        this.summonerRepository = summonerRepository;
        this.httpRequestBuilder = httpRequestBuilder;
        this.http = http;
        this.summonerApi = summonerApi;
    }

    // Anfrage an Summoner-V4. Anfrageparameter ist die bereits gespeicherte PUUID. (speichern ist späterer Entwicklungsschritt)
    // Response-Felder beinhalten ID, AccountID, ProfileIconID, RevisionDate & SummonerLevel.
    private Summoner sendRequest(final String server, final String puuid) throws APIException {
        String summonerEndpoint = http + server + summonerApi + puuid;
        HttpRequest summonerRequest = httpRequestBuilder.createRequest(summonerEndpoint);
        HttpResponse<String> response = httpRequestBuilder.sendRequest(summonerRequest);

        if (response != null) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), Summoner.class);
        } else {
            return null;
        }
    }

    public Summoner saveSummoner(final String server, final String puuid) {
        Summoner summoner = getSummonerByPuuid(puuid);

        if (summoner != null) {
            return summoner;
        } else {

            try {
                summoner = sendRequest(server, puuid);
            } catch (APIException e) {
                e.printStackTrace();
                return null;
            }

            return saveSummoner(summoner);
        }
    }

    @SuppressWarnings("null")
    public Optional<Summoner> getSummonerById(final String id) {
        return summonerRepository.findById(id);
    }

    public List<Summoner> getSummonersByAccountId(final String accountId) {
        return summonerRepository.findByAccountId(accountId);
    }

    public Summoner getSummonerByPuuid(final String puuid) {
        return summonerRepository.findByPuuid(puuid);
    }

    public List<Summoner> getSummonersByName(final String name) {
        return summonerRepository.findByName(name);
    }

    public Summoner saveSummoner(final Summoner summoner) {
        return summonerRepository.save(summoner);
    }
}
