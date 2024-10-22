package com.archery.osugrabber.osu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Osu {

    private String apiKey;
    private final String apiBase = "https://osu.ppy.sh/api/";
    private final String getUserBase = "get_user?u=";
    private final String getUserBestBase = "get_user_best?u=";
    private final String getBeatmapBase = "get_beatmaps?b=";
    private final String getBeatmapsetBase = "get_beatmaps?s=";


    private final HttpClient client;
    private final Gson gson = new Gson();

    public Osu(String apiKey) {
        this.apiKey = "&k=" + apiKey;
        client = HttpClient.newHttpClient();
    }

    public User getUser(@NotNull String username, boolean debug) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getUserBase + username + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement userJson = gson.fromJson(response.body(), JsonElement.class);

        if(debug) System.out.println(userJson.toString());

        return gson.fromJson(userJson.getAsJsonArray().get(0).getAsJsonObject(), User.class);
    }

    public User getUser(@NotNull String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getUserBase + username + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement userJson = gson.fromJson(response.body(), JsonElement.class);

        return gson.fromJson(userJson.getAsJsonArray().get(0).getAsJsonObject(), User.class);
    }
    /*
    *
    * @TODO Make getBest use getBeatmap to retrieve title
    *   and gson.fromJson take in title and player (user) data.
    *
    * */

    public List<Play> getBest(@NotNull User user, int limit) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getUserBestBase + user.getUserID() + "&limit=" + limit + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement bestJson = gson.fromJson(response.body(), JsonElement.class);
        JsonArray bestArray = bestJson.getAsJsonArray();
        System.out.println(bestArray);
        List<Play> plays = new ArrayList<>();
        bestArray.forEach(play -> {
            plays.add(gson.fromJson(play, Play.class));
        });
        return plays;
    }

    public List<Play> getBest(@NotNull User user) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getUserBestBase + user.getUserID() + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement bestJson = gson.fromJson(response.body(), JsonElement.class);
        JsonArray bestArray = bestJson.getAsJsonArray();
        List<Play> plays = new ArrayList<>();
        bestArray.forEach(play -> {
            plays.add(gson.fromJson(play, Play.class));
        });
        return plays;
    }

    public Beatmap getBeatmap(int beatmapID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getBeatmapBase + beatmapID + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement beatmapJson = gson.fromJson(response.body(), JsonElement.class);
        return gson.fromJson(beatmapJson, Beatmap.class);
    }

    public Beatmap getBeatmapSet(int beatmapsetID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getBeatmapsetBase + beatmapsetID + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement beatmapJson = gson.fromJson(response.body(), JsonElement.class);
        return gson.fromJson(beatmapJson, Beatmap.class);
    }

    public void setAPIKey(String apiKey) {
        this.apiKey = "&k=" + apiKey;
    }
}
