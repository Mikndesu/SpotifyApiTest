import io.javalin.Javalin;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Main {
    public static Thread thread = new Thread() {
        @Override
        synchronized public void run() {
            URI uri = new AuthorizationCodeUri().authorizationCodeUri();
            LocalServer.Init();
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(uri.toString()));
                wait();
            } catch (IOException | URISyntaxException | InterruptedException e) {
                e.printStackTrace();
            }
            var req = SpotifySingleton.spotifyApi.getAlbum("3T4tUhGYeRNVUGevb0wThu").build();
            try {
                System.out.println(Arrays.toString(req.execute().getArtists()));
                LocalServer.stopServer();
            } catch (IOException | SpotifyWebApiException | ParseException e) {
                e.printStackTrace();
            }
        }
    };
    public static void main(String[] args) throws IOException, ParseException, SpotifyWebApiException {
        thread.start();
    }
}
