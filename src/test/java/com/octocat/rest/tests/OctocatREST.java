package com.octocat.rest.tests;

import com.octocat.rest.OctocatDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.springframework.http.HttpStatus;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;


public class OctocatREST {
    protected Response response;
    protected OctocatDTO octocatDTO;

    public OctocatREST(){
        String url = "https://api.github.com/users/octocat";
        RestAssured.defaultParser = Parser.JSON;
        response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .get(url)
                .andReturn();
    }

    @Test
    public void doGetOctocat(){
        response.then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .log()
                .all();
    }

    @Test (dependsOnMethods = { "doGetOctocat" })
    public void buildDataFromJSONResponse() {
        String respJSON = response.getBody().asString(), elValue;
        System.out.println("RESPONSE JSON \n" + respJSON);
        Map<String, Object> mapValues = response.jsonPath().getJsonObject("");
        assertThat("Map of response json object values is empty", mapValues.size() > 0);
        assertThat("Number of keys was changed since last time", mapValues.keySet().size() == 32);
        for (String key : mapValues.keySet()) {
            elValue = String.valueOf(mapValues.get(key));
            System.out.println("key " + key + " ,value " + elValue);
        }
        System.out.println("Start create object of class OctacatDTO from values of response body JSON");
        octocatDTO = OctocatDTO.builder()
                .login(String.valueOf(mapValues.get("login")))
                .id(String.valueOf(mapValues.get("id")))
                .node_id(String.valueOf(mapValues.get("node_id")))
                .avatar_url(String.valueOf(mapValues.get("avatar_url")))
                .gravatar_id(String.valueOf(mapValues.get("gravatar_id")))
                .url(String.valueOf(mapValues.get("url")))
                .html_url(String.valueOf(mapValues.get("html_url")))
                .followers_url(String.valueOf(mapValues.get("followers_url")))
                .following_url(String.valueOf(mapValues.get("following_url")))
                .gists_url(String.valueOf(mapValues.get("gists_url")))
                .starred_url(String.valueOf(mapValues.get("starred_url")))
                .subscriptions_url(String.valueOf(mapValues.get("subscriptions_url")))
                .organizations_url(String.valueOf(mapValues.get("organizations_url")))
                .repos_url(String.valueOf(mapValues.get("repos_url")))
                .events_url(String.valueOf(mapValues.get("events_url")))
                .received_events_url(String.valueOf(mapValues.get("received_events_url")))
                .type(String.valueOf(mapValues.get("type")))
                .site_admin(mapValues.get("site_admin").equals(true))
                .name(String.valueOf(mapValues.get("name")))
                .company(String.valueOf(mapValues.get("company")))
                .blog(String.valueOf(mapValues.get("blog")))
                .location(String.valueOf(mapValues.get("location")))
                .email(mapValues.get("email") != null ? String.valueOf(mapValues.get("email")) : null)
                .hireable(mapValues.get("hireable") != null ? String.valueOf(mapValues.get("hireable")) : null)
                .bio(mapValues.get("bio") != null ? String.valueOf(mapValues.get("bio")) : null)
                .twitter_username(mapValues.get("twitter_username") != null ? String.valueOf(mapValues.get("twitter_username")) : null)
                .public_repos(Integer.valueOf(String.valueOf(mapValues.get("public_repos"))))
                .public_gists(Integer.valueOf(String.valueOf(mapValues.get("public_gists"))))
                .followers(Integer.valueOf(String.valueOf(mapValues.get("followers"))))
                .following(Integer.valueOf(String.valueOf(mapValues.get("following"))))
                .created_at(String.valueOf(mapValues.get("created_at")))
                .updated_at(String.valueOf(mapValues.get("updated_at")))
                .build();
        System.out.println("Object of octacatDTO created");
    }

    @Test (dependsOnMethods = { "buildDataFromJSONResponse" })
    public void assertObjectDTOValues(){
        System.out.println("Start checking values of response JSON object");
        assertThat("Login was changed", octocatDTO.getLogin().equals("octocat"));
        assertThat("Id was changed", octocatDTO.getId().equals("583231"));
        assertThat("Node id was changed", octocatDTO.getNode_id().equals("MDQ6VXNlcjU4MzIzMQ=="));
        assertThat("Avatar url was changed", octocatDTO.getAvatar_url().equals("https://avatars.githubusercontent.com/u/583231?v=4"));
        assertThat("Gravatar id was changed", octocatDTO.getGravatar_id().equals(""));
        assertThat("Url was changed", octocatDTO.getUrl().equals("https://api.github.com/users/octocat"));
        assertThat("HTML url was changed", octocatDTO.getHtml_url().equals("https://github.com/octocat"));
        assertThat("followers_url was changed", octocatDTO.getFollowers_url().equals("https://api.github.com/users/octocat/followers"));
        assertThat("following_url was changed", octocatDTO.getFollowing_url().equals("https://api.github.com/users/octocat/following{/other_user}"));
        assertThat("gists_url was changed", octocatDTO.getGists_url().equals("https://api.github.com/users/octocat/gists{/gist_id}"));
        assertThat("starred_url was changed", octocatDTO.getStarred_url().equals("https://api.github.com/users/octocat/starred{/owner}{/repo}"));
        assertThat("subscriptions_url was changed", octocatDTO.getSubscriptions_url().equals("https://api.github.com/users/octocat/subscriptions"));
        assertThat("organizations_url was changed", octocatDTO.getOrganizations_url().equals("https://api.github.com/users/octocat/orgs"));
        assertThat("repos_url was changed", octocatDTO.getRepos_url().equals("https://api.github.com/users/octocat/repos"));
        assertThat("events_url was changed", octocatDTO.getEvents_url().equals("https://api.github.com/users/octocat/events{/privacy}"));
        assertThat("received_events_url was changed", octocatDTO.getReceived_events_url().equals("https://api.github.com/users/octocat/received_events"));
        assertThat("Type was changed", octocatDTO.getType().equals("User"));
        assertThat("Site admin was changed", !octocatDTO.getSite_admin());
        assertThat("Name was changed", octocatDTO.getName().equals("The Octocat"));
        assertThat("Company was changed", octocatDTO.getCompany().equals("@github"));
        assertThat("Blog was changed", octocatDTO.getBlog().equals("https://github.blog"));
        assertThat("Location was changed", octocatDTO.getLocation().equals("San Francisco"));
        assertThat("Email was changed", octocatDTO.getEmail() == null);
        assertThat("Hireable id was changed", octocatDTO.getHireable() == null);
        assertThat("Bio was changed", octocatDTO.getBio() == null);
        assertThat("Twitter username was changed", octocatDTO.getTwitter_username() == null);
        assertThat("Public repos was changed", octocatDTO.getPublic_repos() == 8);
        assertThat("Public gists was changed", octocatDTO.getPublic_gists() == 8);
        assertThat("Followers was changed", octocatDTO.getFollowers() >= 3881);
        assertThat("Following was changed", octocatDTO.getFollowing() == 9);
        assertThat("Created at was changed", octocatDTO.getCreated_at().equals("2011-01-25T18:44:36Z"));
        assertThat("Updated at was changed", octocatDTO.getUpdated_at().equals("2021-07-22T14:27:29Z"));
        System.out.println("Stop checking values of response JSON object");
    }
}
