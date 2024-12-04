package com.electra;

import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Base64;

public class RedditApp {

    private static final String CLIENT_ID = "ur_id";
    private static final String CLIENT_SECRET = "ur_secret";
    private static final String USERNAME = "ur_username";
    private static final String PASSWORD = "ur_password";

    private static String accessToken;

    public static void main(String[] args) {
        try {
            authenticate();
            if (accessToken != null) {
                fetchSubredditPosts("java");
            } else {
                System.err.println("Authentication failed. Cannot fetch posts.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void authenticate() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "password")
                .add("username", USERNAME)
                .add("password", PASSWORD)
                .build();

        Request request = new Request.Builder()
                .url("https://www.reddit.com/api/v1/access_token")
                .addHeader("Authorization", basicAuth)
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                System.out.println("Response from Reddit API: " + responseBody);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(responseBody);

                if (jsonNode.has("access_token")) {
                    accessToken = jsonNode.get("access_token").asText();
                    System.out.println("Authentication successful! Access token: " + accessToken);
                } else {
                    System.err.println("Error: No access_token in response. Response details: " + responseBody);
                }
            } else {
                System.err.println("Authentication failed: " + response.message());
                if (response.body() != null) {
                    System.err.println("Response body: " + response.body().string());
                }
            }
        }
    }

    private static void fetchSubredditPosts(String subreddit) throws IOException {
        if (accessToken == null) {
            System.err.println("Cannot fetch posts without an access token.");
            return;
        }

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://oauth.reddit.com/r/" + subreddit + "/hot?limit=10")
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("User-Agent", "RedditApp/1.0")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(responseBody);

                JsonNode posts = jsonNode.path("data").path("children");
                System.out.println("Top posts from r/" + subreddit + ":");

                for (JsonNode post : posts) {
                    String title = post.path("data").path("title").asText("No Title");
                    System.out.println("- " + title);
                }
            } else {
                System.err.println("Failed to fetch subreddit posts: " + response.message());
                if (response.body() != null) {
                    System.err.println("Response body: " + response.body().string());
                }
            }
        }
    }
}
