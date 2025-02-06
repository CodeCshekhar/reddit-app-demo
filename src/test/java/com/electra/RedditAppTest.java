package com.electra;

import okhttp3.*;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import java.io.IOException;

public class RedditAppTest {

    @Mock
    private OkHttpClient mockClient;

    private RedditApp redditApp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        redditApp = new RedditApp();
    }

    // 1. Test authenticate method
    @Test
    public void testAuthenticate_success() throws IOException {
        // Prepare mock response for authentication
        String mockResponse = "{\"access_token\":\"mock_access_token\",\"token_type\":\"bearer\"}";
        Response mockResponseObject = new Response.Builder()
                .code(200)
                .message("OK")
                .request(new Request.Builder().url("https://www.reddit.com/api/v1/access_token").build())
                .body(ResponseBody.create(mockResponse, MediaType.parse("application/json")))
                .build();

        // Mock the new call request
        when(mockClient.newCall(any(Request.class))).thenReturn(new Call() {
            @NotNull
            @Override
            public Call clone() {
                return null;
            }

            @NotNull
            @Override
            public Timeout timeout() {
                return null;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void enqueue(@NotNull Callback callback) {

            }

            @NotNull
            @Override
            public Request request() {
                return null;
            }

            @Override
            public void cancel() {

            }

            @Override
            public Response execute() throws IOException {
                return mockResponseObject;
            }
        });

        // Inject mock client into RedditApp
        redditApp.setClient(mockClient);

        // Authenticate the app
        redditApp.authenticate();

        // Verify that the access token was successfully set
        Assertions.assertNotNull(redditApp.getAccessToken());
        Assertions.assertEquals("mock_access_token", redditApp.getAccessToken());
    }

    // 2. Test fetchSubredditPosts method (Fetching posts from a subreddit)
    @Test
    public void testFetchSubredditPosts_success() throws IOException {
        // Prepare mock response for fetching subreddit posts
        String mockPostResponse = "{ \"data\": { \"children\": [ { \"data\": { \"title\": \"Post 1\" } }, { \"data\": { \"title\": \"Post 2\" } } ] } }";
        Response mockResponseObject = new Response.Builder()
                .code(200)
                .message("OK")
                .request(new Request.Builder().url("https://oauth.reddit.com/r/test/hot?limit=10").build())
                .body(ResponseBody.create(mockPostResponse, MediaType.parse("application/json")))
                .build();

        // Mock the new call request for subreddit posts
        when(mockClient.newCall(any(Request.class))).thenReturn(new Call() {
            @NotNull
            @Override
            public Call clone() {
                return null;
            }

            @NotNull
            @Override
            public Timeout timeout() {
                return null;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void enqueue(@NotNull Callback callback) {

            }

            @NotNull
            @Override
            public Request request() {
                return null;
            }

            @Override
            public void cancel() {

            }

            @Override
            public Response execute() throws IOException {
                return mockResponseObject;
            }
        });

        // Inject mock client into RedditApp
        redditApp.setClient(mockClient);
        redditApp.authenticate();  // Ensure authentication is done before fetching posts

        // Fetch posts from a subreddit
        redditApp.fetchSubredditPosts("test");

        // Verifying if the posts were printed
        // (You could also use a custom output stream to capture the System.out output)
        verify(mockClient, times(1)).newCall(any(Request.class));
    }

    // 3. Test fetchSubredditPosts method (when authentication fails)
    @Test
    public void testFetchSubredditPosts_noAuth() throws IOException {
        // Test the case when no access token is available (authentication fails)
        redditApp.setAccessToken(null);

        // Try fetching posts without authentication
        redditApp.fetchSubredditPosts("test");

        // Verify that the mock call is not executed
        verify(mockClient, times(0)).newCall(any(Request.class));
    }

    // 4. Test authenticate method - failure case (invalid credentials)
    @Test
    public void testAuthenticate_failure() throws IOException {
        // Prepare a mock failure response for authentication
        String mockErrorResponse = "{\"error\": \"invalid_grant\"}";
        Response mockResponseObject = new Response.Builder()
                .code(400)
                .message("Bad Request")
                .request(new Request.Builder().url("https://www.reddit.com/api/v1/access_token").build())
                .body(ResponseBody.create(mockErrorResponse, MediaType.parse("application/json")))
                .build();

        // Mock the new call request for failure
        when(mockClient.newCall(any(Request.class))).thenReturn(new Call() {
            @NotNull
            @Override
            public Call clone() {
                return null;
            }

            @NotNull
            @Override
            public Timeout timeout() {
                return null;
            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void enqueue(@NotNull Callback callback) {

            }

            @NotNull
            @Override
            public Request request() {
                return null;
            }

            @Override
            public void cancel() {

            }

            @Override
            public Response execute() throws IOException {
                return mockResponseObject;
            }
        });

        // Inject mock client into RedditApp
        redditApp.setClient(mockClient);

        // Attempt to authenticate with invalid credentials
        redditApp.authenticate();

        // Verify that the access token is not set and the error is logged
        Assertions.assertNull(redditApp.getAccessToken());
    }
}
