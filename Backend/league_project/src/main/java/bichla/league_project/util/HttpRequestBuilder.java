package bichla.league_project.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bichla.league_project.exceptions.APIException;

/**
 * 
 * 
 * @author      Manuel Losbichler
 * @version     %I%, %G%
 */
@Component
public class HttpRequestBuilder {

    private final String apiKey;

    @Autowired
    public HttpRequestBuilder(final String apiKey) {
        this.apiKey = apiKey;
    }

    public HttpRequest createRequest(final String endpointString) {
        return HttpRequest.newBuilder()
        .uri(URI.create(endpointString))
        .header("X-Riot-Token", apiKey)
        .build();
    }

    public HttpResponse<String> sendRequest(final HttpRequest request) throws APIException {
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
}
