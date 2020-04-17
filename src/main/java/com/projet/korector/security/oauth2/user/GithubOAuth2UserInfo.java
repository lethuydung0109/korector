package com.projet.korector.security.oauth2.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserInfo {
    private final URI URI_API_EMAILS;
    private RestTemplate restTemplate;
    private final ParameterizedTypeReference<List<Object>> TYPE_REF_LIST_OBJECT = new ParameterizedTypeReference<List<Object>>() { };


    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        try {
            URI_API_EMAILS = new URI("https://api.github.com/user/emails");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getId() {
        return ((Integer) attributes.get("id")).toString();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getUserName() {
        return (String) attributes.get("login");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("avatar_url");
    }

    @Override
    public String getHtmlURL() {
        return (String) attributes.get("html_url");
    }

    //    public List<Object> getUserEmails(final String accessToken) {
    public List<Object> getUserEmails(final String accessToken) {
        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "token " + accessToken);
        RequestEntity<String> entity1 = new RequestEntity<>(headers, HttpMethod.GET, URI_API_EMAILS);

        ResponseEntity<List<Object>> responseEntity = restTemplate.exchange(entity1, TYPE_REF_LIST_OBJECT);

        if (responseEntity.getStatusCode().is4xxClientError() || responseEntity.getStatusCode().is5xxServerError()) {
//            throw new ApiException(responseEntity);
            System.out.println("Error in getUserEmails");
        }

//        LOGGER.info("API response [{}]", responseEntity);

        return responseEntity.getBody();
    }

}