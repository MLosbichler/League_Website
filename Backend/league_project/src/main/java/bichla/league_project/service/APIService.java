package bichla.league_project.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import bichla.league_project.exceptions.APIException;

/**
 * 
 */
@Service
public class APIService {
    // Riot-Games-API related fields. Probably later changed to environmental values.
    private final String API_KEY = "";
    private final String HTTP = "https://";
    private final String ACCOUNT_API = ".api.riotgames.com/riot/account/v1/accounts/by-riot-id/";
    private final String SUMMONER_API = ".api.riotgames.com/lol/summoner/v4/summoners/by-puuid/";
    private final String LEAGUE_API = ".api.riotgames.com/lol/league/v4/entries/by-summoner/";

    // Account-related fields. Will be input presented by the frontend later.
    private String summonerName = "viechlich69";
    private String tagLine = "EPHC";
    private String region = "euw1";
    private String continent = "europe";

    public void test() {
        
        try {
            System.out.println("Account: ");
            String puuid = sendAccountRequest();
            System.out.println(puuid);
            System.out.println("-------------------------------------------------------------------");
            String id = sendSummonerRequest(puuid);
            System.out.println("Summoner: ");
            System.out.println(id);
            System.out.println("-------------------------------------------------------------------");
            System.out.println("League: ");
            String elo = sendLeagueRequest(id);
            System.out.println(elo);
        } catch (APIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private HttpRequest createRequest(final String endpointString) {
        return HttpRequest.newBuilder()
        .uri(URI.create(endpointString))
        .header("X-Riot-Token", API_KEY)
        .build();
    }

    private String sendRequest(HttpRequest request) throws APIException {
        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            if (statusCode == 200) {
                return response.body(); // TODO change to JSON response handling.
            } else {
                throw new APIException("Failed API-Call: " + statusCode);
            }
        } catch (Exception e) {
            throw new APIException("Failed API-Call: " + e.getMessage());
        }
    }

    // Anfrage an Account-V1. Kontinent ist eigentlich irrelevant, Riot empfiehlt trotzdem dort den richtigen Wert zu nehmen.
    // Einziges wirkliches Response-Feld ist die PUUID des angefragten Accounts.
    private String sendAccountRequest() throws APIException {
        String accountEndpoint = HTTP + continent + ACCOUNT_API + summonerName + "/" + tagLine;
        HttpRequest accountRequest = createRequest(accountEndpoint);
        String response = sendRequest(accountRequest);

        if (response != null) {
            return response.split("\"")[3];
        } else {
            return null;
        }
    }

    // Anfrage an Summoner-V4. Anfrageparameter ist die bereits gespeicherte PUUID. (speichern ist späterer Entwicklungsschritt)
    // Response-Felder beinhalten ID, AccountID, ProfileIconID, RevisionDate & SummonerLevel.
    private String sendSummonerRequest(final String puuid) throws APIException {
        String summonerEndpoint = HTTP + region + SUMMONER_API + puuid;
        HttpRequest summonerRequest = createRequest(summonerEndpoint);
        String response = sendRequest(summonerRequest);

        if (response != null) {
            String id = response.split("\"")[3];
            return id;
        } else {
            return null;
        }
    }

    // Anfrage an League-V4. Anfrageparameter ist die bereits gespeicherte ID. (speichern ist späterer Entwicklungsschritt)
    // Response-Felder beinhalten QueueType, Tier, Rank, LeaguePoints, Wins & Losses.
    private String sendLeagueRequest(final String summonerId) throws APIException {
        String leagueEndpoint = HTTP + region + LEAGUE_API + summonerId;
        HttpRequest leagueRequest = createRequest(leagueEndpoint);
        String response = sendRequest(leagueRequest);

        if (response != null) {
            String tier = response.split("\"")[11];
            String division = response.split("\"")[15];
            String leaguePoints = response.split("\"")[26]; // TODO regex to strip everything from the number.
            String wins = response.split("\"")[28]; // TODO regex to strip everything from the number.
            String losses = response.split("\"")[30]; // TODO regex to strip everything from the number.
            return tier + " " + division + " " + leaguePoints + " LP, " + wins + " Wins, " + losses + "Losses";
        } else {
            return null;
        }
    }
}
