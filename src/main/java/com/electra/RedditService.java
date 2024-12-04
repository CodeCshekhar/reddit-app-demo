package com.electra;

import okhttp3.*;

import java.io.IOException;

public class RedditService {
    public void fetchUserInfo(String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://oauth.reddit.com/api/v1/me")
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("User-Agent", "RedditApp/1.0")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("User Info: " + response.body().string());
            } else {
                System.err.println("Error: " + response.message());
            }
        }
    }
}
