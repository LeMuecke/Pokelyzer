import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

public class APIScraper {

    public Pokelyzer pokelyzer;
    private String pokes;
    private double longitude;
    private double latitude;

    public APIScraper(Pokelyzer pokelyzer, double latitude, double longitude) {
        this.pokelyzer = pokelyzer;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void writePokemonToDatabase() {
        pokes = readFromPokevision();
        System.out.println(pokes);

        pokes = pokes.substring(pokes.indexOf('['));
        pokes = pokes.substring(0,pokes.length()-2);

        if(pokes.equals("[")) return;     //TODO: Make this more elegant

        String array[] = pokes.split(",");

        for (int i = 0; i < array.length; i = i + 5) {
            Pokemon pokemon = new Pokemon(Integer.parseInt(array[i].split(":")[1]),
                    Integer.parseInt(array[i + 3].split(":")[1]),
                    Integer.parseInt(array[i + 4].split(":")[1].substring(0,array[i + 4].split(":")[1].length()-1)),
                    (int)(System.currentTimeMillis()/1000),
                    new Coordinate(Double.parseDouble(array[i + 1].split(":")[1]),Double.parseDouble(array[i + 2].split(":")[1])));
            pokelyzer.getPokemonDatabase().addPokemon(pokemon);
        }

    }


    public String readFromPokevision() {
        HttpsURLConnection conn;
        try {
            conn = (HttpsURLConnection) (new URL("https://pokevision.com/map/data/" + latitude + "/" +  longitude).openConnection());
        } catch (IOException e) {
            System.out.println("Connection to Pokevision could not be established! Aborting.");
            e.printStackTrace();
            return "";
        }
        conn.setRequestProperty("User-Agent", "curl/7.50.0");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } catch (IOException e) {
            System.out.println("Reading from Pokevision failed! Aborting.");
            e.printStackTrace();
            return "";
        }
        try {
            conn.connect();
        } catch (IOException e) {
            System.out.println("Connecting to Pokevision failed! Aborting.");
            e.printStackTrace();
            return "";
        }
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println("Reading from Pokevision failed! Aborting.");
            e.printStackTrace();
            return "";
        }

        return line;

    }

}