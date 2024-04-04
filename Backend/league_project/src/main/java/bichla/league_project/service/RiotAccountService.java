package bichla.league_project.service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import bichla.league_project.exceptions.APIException;
import bichla.league_project.model.RiotAccount;
import bichla.league_project.repository.RiotAccountRepository;
import bichla.league_project.util.HttpRequestBuilder;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Service
public class RiotAccountService {
    private final RiotAccountRepository riotAccountRepository;
    private final HttpRequestBuilder httpRequestBuilder;
    private final String http;
    private final String accountApiName;

    public RiotAccountService(final RiotAccountRepository riotAccountRepository,
                                final HttpRequestBuilder httpRequestBuilder,
                                final String http,
                                final String accountApiName) {
        this.riotAccountRepository = riotAccountRepository;
        this.httpRequestBuilder = httpRequestBuilder;
        this.http = http;
        this.accountApiName = accountApiName;

    }

    private RiotAccount sendRequest(final String continent, final String summonerName, final String tagLine) throws APIException {
        String request = http + continent + accountApiName + summonerName + "/" + tagLine;
        HttpRequest accountRequest = httpRequestBuilder.createRequest(request);
        HttpResponse<String> response = httpRequestBuilder.sendRequest(accountRequest);

        if (response != null) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), RiotAccount.class);
        } else {
            return null;
        }
    }

    public RiotAccount saveRiotAccount(final String continent, final String summonerName, final String tagLine) {
        RiotAccount account = getAccountByName(summonerName, tagLine);

        // Name changes might lead to differences between PUUIDs and names.
        if (account != null) {
            return account;
        } else {

            try {
                account = sendRequest(continent, summonerName, tagLine);
            } catch (APIException e) {
                e.printStackTrace();
                return null;
            }

            return saveAccount(account);
        }
    }

    public RiotAccount getAccountByPuuid(final String puuid) {
        return riotAccountRepository.findByPuuid(puuid);
    }

    public RiotAccount getAccountByName(final String gameName, final String tagLine) {
        return riotAccountRepository.findByGameNameAndTagLine(gameName, tagLine);
    }

    public RiotAccount saveAccount(final RiotAccount account) {
        return riotAccountRepository.save(account);
    }
}
