package com.electra;

import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Base64;

public class RedditApp {

    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static String accessToken;
    private OkHttpClient client;

    // Set the client for testing purposes
    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    // Get the access token for testing purposes
    public String getAccessToken() {
        return accessToken;
    }
    public String setAccessToken(Object o) {
        return accessToken;
    }

    public static void main(String[] args) {
        try {
            // Step 1: Authenticate
            authenticate();

            // Step 2: Check if authentication is successful
            if (accessToken != null) {
                // Step 3: Fetch posts from a subreddit
                fetchSubredditPosts("Bollywood");

                // Step 4: Post a comment to Reddit
                postToReddit("Test Title", "Test body of the post.");
            } else {
                System.err.println("Authentication failed. Cannot fetch posts or post.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void authenticate() throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Encode client credentials for basic authentication
        String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        // Prepare the form body with the required parameters
        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "password")
                .add("username", USERNAME)
                .add("password", PASSWORD)
                .build();

        // Build the authentication request
        Request request = new Request.Builder()
                .url("https://www.reddit.com/api/v1/access_token")
                .addHeader("Authorization", basicAuth)
                .post(formBody)
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                System.out.println("Response from Reddit API: " + responseBody);

                // Parse the JSON response to extract the access token
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

    public static void fetchSubredditPosts(String subreddit) throws IOException {
        if (accessToken == null) {
            System.err.println("Cannot fetch posts without an access token.");
            return;
        }

        OkHttpClient client = new OkHttpClient();

        // Build the request to fetch subreddit posts
        Request request = new Request.Builder()
                .url("https://oauth.reddit.com/r/" + subreddit + "/hot?limit=10")
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("User-Agent", "RedditApp/1.0")
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();

                // Parse the JSON response to extract post titles
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(responseBody);

                JsonNode posts = jsonNode.path("data").path("children");
                System.out.println("Top posts from r/" + subreddit + ":");

                // Loop through the posts and print their titles
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

    // Method to post a comment or create a new post on Reddit
    public static void postToReddit(String title, String body) throws IOException {
        if (accessToken == null) {
            System.err.println("Cannot post without an access token.");
            return;
        }

        OkHttpClient client = new OkHttpClient();

        // Prepare the form body with required parameters for creating a post
        RequestBody formBody = new FormBody.Builder()
                .add("title", title)
                .add("selftext", body)
                .add("sr", "test") // 'test' subreddit, you can change this to your desired subreddit
                .add("kind", "self") // 'self' indicates it's a text post
                .build();

        // Build the request to create a post
        Request request = new Request.Builder()
                .url("https://oauth.reddit.com/api/submit")
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("User-Agent", "RedditApp/1.0")
                .post(formBody)
                .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                System.out.println("Response from Reddit API (Post creation): " + responseBody);
            } else {
                System.err.println("Failed to create post: " + response.message());
                if (response.body() != null) {
                    System.err.println("Response body: " + response.body().string());
                }
            }
        }
    }
}
