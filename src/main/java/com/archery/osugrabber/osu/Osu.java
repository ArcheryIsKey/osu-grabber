package com.archery.osugrabber.osu;

import com.archery.osugrabber.enums.GameMode;
import com.archery.osugrabber.enums.Mods;
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
import java.util.Arrays;
import java.util.List;

public class Osu {

    private String apiKey;
    private final String apiBase = "https://osu.ppy.sh/api/";
    private final String getUserBase = "get_user?u=";
    private final String getUserBestBase = "get_user_best?u=";
    private final String getBeatmapBase = "get_beatmaps?b=";
    private final String getBeatmapsetBase = "get_beatmaps?s=";

    public boolean isValidKey = false;

    private final HttpClient client;
    private final Gson gson = new Gson();

    public Osu(String apiKey) {
        this.apiKey = "&k=" + apiKey;
        client = HttpClient.newHttpClient();

        /*
        * Check API key works?
        * Should find a better way lol
        * */

        try {
            this.getUser("archeryiskey");
            isValidKey = true;
        } catch (Exception e) {
            System.err.println("Please provide a valid API key.");
        }
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

    public List<Play> getBest(@NotNull User user, int limit) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getUserBestBase + user.getUserID() + "&limit=" + limit + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement bestJson = gson.fromJson(response.body(), JsonElement.class);
        List<Play> plays = getPlays(user, bestJson);

        return plays;
    }

    public List<Play> getBest(@NotNull User user, int limit, GameMode mode) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getUserBestBase + user.getUserID() + "&limit=" + limit  + "&m=" + mode.getID() + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement bestJson = gson.fromJson(response.body(), JsonElement.class);
        List<Play> plays = getPlays(user, bestJson);

        return plays;
    }

    public List<Play> getBest(@NotNull User user) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getUserBestBase + user.getUserID() + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement bestJson = gson.fromJson(response.body(), JsonElement.class);
        List<Play> plays = getPlays(user, bestJson);

        return plays;
    }

    public Play getBestIndex(@NotNull User user, int index) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getUserBestBase + user.getUserID() + "&limit=100" + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement bestJson = gson.fromJson(response.body(), JsonElement.class);
        JsonArray bestArray = bestJson.getAsJsonArray();
        int beatmapID = bestArray.get(index - 1).getAsJsonObject().get("beatmap_id").getAsInt();
        double pp = bestArray.get(index - 1).getAsJsonObject().get("pp").getAsDouble();
        int enabledMods = bestArray.get(index - 1).getAsJsonObject().get("enabled_mods").getAsInt();
        Beatmap beatmap = getBeatmap(beatmapID);

        return new Play(user.getUsername(), beatmap.getArtist(), beatmap.getTitle(), beatmap.getVersion(), Mods.fromListToMods(Mods.toShortened(enabledMods)), pp);
    }

    public Beatmap getBeatmap(int beatmapID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getBeatmapBase + beatmapID + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement beatmapJson = gson.fromJson(response.body(), JsonElement.class);

        return gson.fromJson(beatmapJson.getAsJsonArray().get(0), Beatmap.class);
    }

    public Beatmap getBeatmapSet(int beatmapsetID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBase + getBeatmapsetBase + beatmapsetID + apiKey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonElement beatmapJson = gson.fromJson(response.body(), JsonElement.class);

        return gson.fromJson(beatmapJson, Beatmap.class);
    }

    private @NotNull List<Play> getPlays(@NotNull User user, JsonElement bestJson) {
        JsonArray bestArray = bestJson.getAsJsonArray();
        List<Play> plays = new ArrayList<>();
        bestArray.forEach(play -> {
            try {
                int beatmapID = play.getAsJsonObject().get("beatmap_id").getAsInt();
                double pp = play.getAsJsonObject().get("pp").getAsDouble();
                int enabledMods = play.getAsJsonObject().get("enabled_mods").getAsInt();
                Beatmap beatmap = getBeatmap(beatmapID);
                plays.add(new Play(user.getUsername(), beatmap.getArtist(), beatmap.getTitle(), beatmap.getVersion(), Mods.fromListToMods(Mods.toShortened(enabledMods)), pp));
            } catch (IOException | InterruptedException e) {}
        });
        return plays;
    }

    public void setAPIKey(String apiKey) {
        this.apiKey = "&k=" + apiKey;
    }
}
