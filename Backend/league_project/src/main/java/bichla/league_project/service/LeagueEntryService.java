package bichla.league_project.service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import bichla.league_project.exceptions.APIException;
import bichla.league_project.model.LeagueEntry;
import bichla.league_project.repository.LeagueEntryRepository;
import bichla.league_project.util.HttpRequestBuilder;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Service
public class LeagueEntryService {
    private final LeagueEntryRepository leagueEntryRepository;
    private final HttpRequestBuilder httpRequestBuilder;
    private final String http;
    private final String leagueApi;

    @Autowired
    public LeagueEntryService(final LeagueEntryRepository leagueEntryRepository,
                                final HttpRequestBuilder httpRequestBuilder,
                                final String http,
                                final String leagueApi) {
        this.leagueEntryRepository = leagueEntryRepository;
        this.httpRequestBuilder = httpRequestBuilder;
        this.http = http;
        this.leagueApi = leagueApi;
    }

    // Anfrage an League-V4. Anfrageparameter ist die bereits gespeicherte ID. (speichern ist späterer Entwicklungsschritt)
    // Response-Felder beinhalten QueueType, Tier, Rank, LeaguePoints, Wins & Losses.
    public LeagueEntry sendLeagueRequest(final String server, final String summonerId) throws APIException {
        String leagueEndpoint = http + server + leagueApi + summonerId;
        HttpRequest leagueRequest = httpRequestBuilder.createRequest(leagueEndpoint);
        HttpResponse<String> response = httpRequestBuilder.sendRequest(leagueRequest);

        if (response != null) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), LeagueEntry[].class)[0];
        } else {
            return null;
        }
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
