package custome;

import groovy.transform.ToString;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class TestClass {
    String token = "BQB3u99QHbPQAbp5xaOXPgQIBEQ_IrHytXyISY6IoSEf7XMac43B4v_b_ysUx3ZivrHmuuophiWEAxNwk9nV3HdpW1tU_RNRALK5d68_eLgsLZeMeolktLzHuPQs2DP2l_mZajI05Grv5olr-JukLVXvaBUAmuKhBFFMWbsV11RVXxzidQePQSjO2NXSI4wTFVIDqsAEDpVVMYidmyRl_G3vjU6ez7PjidV4aerQCpSPyjcTGOOtprZ8APv0nHAbzdYRnYCblA";

    @Test
    public void getUsers() {
        Response res = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/me");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }

    @Test
    public void createPlaylist() {
        Response res = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"name\": \"RS PlayList\",\n" +
                        "    \"description\": \"New playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when().post("https://api.spotify.com/v1/users/rtimxak9ehnlcumk6kjxjoykc/playlists");

        res.prettyPrint();
        res.then().assertThat().statusCode(201);
    }

    @Test
    public void addItems() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"uris\": [\n" +
                        "        \"spotify:track:6bvZRLLkBKkmgpBJTTj3QK\"\n" + //track id
                        "    ],\n" +
                        "    \"position\": 1\n" +
                        "}")
                .when().post("https://api.spotify.com/v1/playlists/1NA2bfwhXOWyIxsIE9RX10/tracks"); //playlist ID

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void deleteItem()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"tracks\": [\n" +
                        "        {\n" +
                        "            \"uri\": \"spotify:track:5VMka8joGxOn1R8NJkf3Cl\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"snapshot_id\": \"NSw0NzcxMWMwYTg0ZDk1ZmZjYWRjMzg3YmY1NmExYTM1YjNlNzE1YzRj\"\n" +
                        "}")
                .when().delete("https://api.spotify.com/v1/playlists/1NA2bfwhXOWyIxsIE9RX10/tracks"); //playlist ID

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void updatePlayList()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"range_start\": 0,\n" +
                        "    \"insert_before\": 1,\n" +
                        "    \"range_length\": 1\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/1NA2bfwhXOWyIxsIE9RX10/tracks");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void changePlayListDetails() //need to change
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"name\": \"Updated RS\",\n" +
                        "    \"description\": \"0\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/1NA2bfwhXOWyIxsIE9RX10/tracks");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void getUsersPlaylist()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/users/rtimxak9ehnlcumk6kjxjoykc/playlists");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void getPlayListItems()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/playlists/1NA2bfwhXOWyIxsIE9RX10/tracks");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void getCurrentUsersPlayList()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void getFeaturedPlaylists()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/browse/featured-playlists");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void getCategorysPlaylists() //find catagories first
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/{category_id}/playlists");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void getPlaylistCoverImage()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/playlists/1NA2bfwhXOWyIxsIE9RX10/images");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void addCustomPlaylistCoverImage() //need to chk
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .put("https://api.spotify.com/v1/playlists/1NA2bfwhXOWyIxsIE9RX10/images");
        res.prettyPrint();
        res.then().assertThat().statusCode(202);
    }
    //--------------------------------------------------------------------------------------------------------
    //users section
    @Test
    public void getCurrentUsersprofile() //need to chk
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void  getUsersTopItems()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/top/tracks");
        res.prettyPrint();
        res.then().assertThat().statusCode(403);
    }
    @Test
    public void getUsersProfile()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/users/RS");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void followPlaylist()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/1NA2bfwhXOWyIxsIE9RX10/followers");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void unfollowPlaylist()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("https://api.spotify.com/v1/playlists/1NA2bfwhXOWyIxsIE9RX10/followers");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
    @Test
    public void getFollowedArtists()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .put("https://api.spotify.com/v1/me/following");
        res.prettyPrint();
        res.then().assertThat().statusCode(401);
    }
    @Test
    public void followArtistsorUsers()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"ids\":41MozSoPIsD1dJM0CLPjZF [\n" +
                        "        \"abc\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/following");
        res.prettyPrint();
        res.then().assertThat().statusCode(403);
    }
    @Test
    public void unfollowArtistsorUsers()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"ids\":41MozSoPIsD1dJM0CLPjZF [\n" +
                        "        \"abc\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/following");
        res.prettyPrint();
        res.then().assertThat().statusCode(403);
    }
    @Test
    public void checkIfUserFollowsArtistsorUsers()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"ids\":41MozSoPIsD1dJM0CLPjZF [\n" +
                        "        \"abc\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/following/contains");
        res.prettyPrint();
        res.then().assertThat().statusCode(405);
    }
    @Test
    public void checkifUsersFollowPlaylist()
    {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"ids\":41MozSoPIsD1dJM0CLPjZF [\n" +
                        "        \"abc\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/1NA2bfwhXOWyIxsIE9RX10/followers/contains");
        res.prettyPrint();
        res.then().assertThat().statusCode(405);
    }
}
