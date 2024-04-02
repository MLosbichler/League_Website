package bichla.league_project.service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    private final String accountApi;

    @Autowired
    public RiotAccountService(final RiotAccountRepository riotAccountRepository,
                                final HttpRequestBuilder httpRequestBuilder,
                                final String http,
                                final String accountApi) {
        this.riotAccountRepository = riotAccountRepository;
        this.httpRequestBuilder = httpRequestBuilder;
        this.http = http;
        this.accountApi = accountApi;
    }

    // Anfrage an Account-V1. Kontinent ist eigentlich irrelevant, Riot empfiehlt trotzdem dort den richtigen Wert zu nehmen.
    // Einziges wirkliches Response-Feld ist die PUUID des angefragten Accounts.
    public RiotAccount sendAccountRequest(final String continent, final String summonerName, final String tagLine) throws APIException {
        String accountEndpoint = http + continent + accountApi + summonerName + "/" + tagLine;
        HttpRequest accountRequest = httpRequestBuilder.createRequest(accountEndpoint);
        HttpResponse<String> response = httpRequestBuilder.sendRequest(accountRequest);

        if (response != null) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), RiotAccount.class);
        } else {
            return null;
        }
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
