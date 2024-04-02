package bichla.league_project.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import bichla.league_project.exceptions.APIException;
import bichla.league_project.model.LeagueEntry;
import bichla.league_project.model.RiotAccount;
import bichla.league_project.model.Summoner;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Service
public class APIService {
    // Riot-Games-API related fields. Probably later changed to environmental values.
    private final String API_KEY = "RGAPI-134558e4-fb4a-4df2-81a6-e953ec21337b";
    private final String HTTP = "https://";
    private final String ACCOUNT_API = ".api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
    private final String SUMMONER_API = ".api.riotgames.com/lol/summoner/v4/summoners/by-puuid/";
    private final String LEAGUE_API = ".api.riotgames.com/lol/league/v4/entries/by-summoner/";

    private HttpRequest createRequest(final String endpointString) {
        return HttpRequest.newBuilder()
        .uri(URI.create(endpointString))
        .header("X-Riot-Token", API_KEY)
        .build();
    }

    private HttpResponse<String> sendRequest(HttpRequest request) throws APIException {
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            if (statusCode == 200) {
                return response;
            } else {
                throw new APIException("Failed API-Call: " + statusCode);
            }
        } catch (Exception e) {
            throw new APIException("Failed API-Call: " + e.getMessage());
        }
    }

    // Anfrage an Account-V1. Kontinent ist eigentlich irrelevant, Riot empfiehlt trotzdem dort den richtigen Wert zu nehmen.
    // Einziges wirkliches Response-Feld ist die PUUID des angefragten Accounts.
    public RiotAccount sendAccountRequest(final String continent, final String summonerName, final String tagLine) throws APIException {
        String accountEndpoint = HTTP + continent + ACCOUNT_API + summonerName + "/" + tagLine;
        HttpRequest accountRequest = createRequest(accountEndpoint);
        HttpResponse<String> response = sendRequest(accountRequest);

        if (response != null) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), RiotAccount.class);
        } else {
            return null;
        }
    }

    // Anfrage an Summoner-V4. Anfrageparameter ist die bereits gespeicherte PUUID. (speichern ist späterer Entwicklungsschritt)
    // Response-Felder beinhalten ID, AccountID, ProfileIconID, RevisionDate & SummonerLevel.
    public Summoner sendSummonerRequest(final String server, final String puuid) throws APIException {
        String summonerEndpoint = HTTP + server + SUMMONER_API + puuid;
        HttpRequest summonerRequest = createRequest(summonerEndpoint);
        HttpResponse<String> response = sendRequest(summonerRequest);

        if (response != null) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), Summoner.class);
        } else {
            return null;
        }
    }

    // Anfrage an League-V4. Anfrageparameter ist die bereits gespeicherte ID. (speichern ist späterer Entwicklungsschritt)
    // Response-Felder beinhalten QueueType, Tier, Rank, LeaguePoints, Wins & Losses.
    public LeagueEntry sendLeagueRequest(final String server, final String summonerId) throws APIException {
        String leagueEndpoint = HTTP + server + LEAGUE_API + summonerId;
        HttpRequest leagueRequest = createRequest(leagueEndpoint);
        HttpResponse<String> response = sendRequest(leagueRequest);

        if (response != null) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), LeagueEntry[].class)[0];
        } else {
            return null;
        }
    }
}
