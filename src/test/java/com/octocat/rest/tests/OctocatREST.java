package com.octocat.rest.tests;

import com.octocat.rest.OctocatModel;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;


public class OctocatREST {
    private final String url = "https://api.github.com/users/octocat";
    protected Response response = null;

    public OctocatREST(){
        response = given().get(url).andReturn();
    }

    @Test
    public void getOctocat_whenURLGiven(){
        response.then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .log()
                .all();
    }

    @Test
    public void checkDataJSONResponse(){
        String respJSON = response.getBody().asString(), elValue;
        System.out.println("RESPONSE JSON \n" + respJSON);
        Map<String, String> mapValues = response.jsonPath().getJsonObject("");
        assertThat("Mapa wartości jest pusta", mapValues.size() > 0);
        for(String key : mapValues.keySet()){
            elValue = String.valueOf(mapValues.get(key));
            System.out.println("key " + key + " ,value " + elValue);
        }
//        OctocatModel octocatModel = OctocatModel.builder()
//                .login(mapValues.get("login"))
//                .id(String.valueOf(mapValues.get("id")))
//                .node_id(mapValues.get("node_id"))
//                .avatar_url(mapValues.get("avatar_url"))
//                .gravatar_id(mapValues.get("gravatar_id"))
//                .url(mapValues.get("url"))
//                .html_url(mapValues.get("html_url"))
//                .followers_url(mapValues.get("followers_url"))
//                .following_url(mapValues.get("following_url"))
//                .gists_url(mapValues.get("gists_url"))
//                .starred_url(mapValues.get("starred_url"))
//                .subscriptions_url(mapValues.get("subscriptions_url"))
//                .organizations_url(mapValues.get("organizations_url"))
//                .repos_url(mapValues.get("repos_url"))
//                .events_url(mapValues.get("events_url"))
//                .received_events_url(mapValues.get("received_events_url"))
//                .type(mapValues.get("type"))
//                .site_admin(String.valueOf(mapValues.get("site_admin")))
//                .name(mapValues.get("name"))
//                .company(mapValues.get("company"))
//                .blog(mapValues.get("blog"))
//                .location(mapValues.get("location"))
//                .email(mapValues.get("email"))
//                .hireable(mapValues.get("hireable"))
//                .bio(mapValues.get("bio"))
//                .twitter_username(mapValues.get("twitter_username"))
//                .public_repos(String.valueOf(mapValues.get("public_repos")))
//                .public_gists(String.valueOf(mapValues.get("public_gists")))
//                .followers(String.valueOf(mapValues.get("followers")))
//                .following(String.valueOf(mapValues.get("following")))
//                .created_at(mapValues.get("created_at"))
//                .updated_at(mapValues.get("updated_at"))
//                .build();
        OctocatModel octocatModel = given()
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().as(OctocatModel.class);

        assertThat("Login was changed", octocatModel.getLogin().equals("octocat"));
        assertThat("Id was changed", octocatModel.getId().equals("583231"));
        assertThat("Node id was changed", octocatModel.getNode_id().equals("MDQ6VXNlcjU4MzIzMQ=="));
        assertThat("Avatar url was changed", octocatModel.getAvatar_url().equals("https://avatars.githubusercontent.com/u/583231?v=4"));
        assertThat("Gravatar id was changed", octocatModel.getGravatar_id().equals(""));
        assertThat("Url was changed", octocatModel.getUrl().equals("https://api.github.com/users/octocat"));
        assertThat("HTML url was changed", octocatModel.getHtml_url().equals("https://github.com/octocat"));
        assertThat("followers_url was changed", octocatModel.getFollowers_url().equals("https://api.github.com/users/octocat/followers"));
        assertThat("following_url was changed", octocatModel.getFollowing_url().equals("https://api.github.com/users/octocat/following{/other_user}"));
        assertThat("gists_url was changed", octocatModel.getGists_url().equals("https://api.github.com/users/octocat/gists{/gist_id}"));
        assertThat("starred_url was changed", octocatModel.getStarred_url().equals("https://api.github.com/users/octocat/starred{/owner}{/repo}"));
        assertThat("subscriptions_url was changed", octocatModel.getSubscriptions_url().equals("https://api.github.com/users/octocat/subscriptions"));
        assertThat("organizations_url was changed", octocatModel.getOrganizations_url().equals("https://api.github.com/users/octocat/orgs"));
        assertThat("repos_url was changed", octocatModel.getRepos_url().equals("https://api.github.com/users/octocat/repos"));
        assertThat("events_url was changed", octocatModel.getEvents_url().equals("https://api.github.com/users/octocat/events{/privacy}"));
        assertThat("received_events_url was changed", octocatModel.getReceived_events_url().equals("https://api.github.com/users/octocat/received_events"));
        assertThat("Type was changed", octocatModel.getType().equals("User"));
        assertThat("Site admin was changed", octocatModel.getSite_admin().equals("false"));
        assertThat("Name was changed", octocatModel.getName().equals("The Octocat"));
        assertThat("Company was changed", octocatModel.getCompany().equals("@github"));
        assertThat("Blog was changed", octocatModel.getBlog().equals("https://github.blog"));
        assertThat("Location was changed", octocatModel.getLocation().equals("San Francisco"));
        assertThat("Email was changed", octocatModel.getEmail() == null);
        assertThat("Hireable id was changed", octocatModel.getHireable() == null);
        assertThat("Bio was changed", octocatModel.getBio() == null);
        assertThat("Twitter username was changed", octocatModel.getTwitter_username() == null);
        assertThat("Public repos was changed", octocatModel.getPublic_repos().equals("8"));
        assertThat("Public gists was changed", octocatModel.getPublic_gists().equals("8"));
        assertThat("Followers was changed", octocatModel.getFollowers().equals("3880"));
        assertThat("Following was changed", octocatModel.getFollowing().equals("9"));
        assertThat("Created at was changed", octocatModel.getCreated_at().equals("2011-01-25T18:44:36Z"));
        assertThat("Updated at was changed", octocatModel.getUpdated_at().equals("2021-07-22T14:27:29Z"));
    }
}
