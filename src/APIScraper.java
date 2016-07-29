import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.net.ssl.HttpsURLConnection;

public class APIScraper {

    public Pokelyzer pokelyzer;
    public String pokes;

    public APIScraper(Pokelyzer pokelyzer) {
        this.pokelyzer = pokelyzer;
    }

    public void writePokemonToDatabase() {
        pokes = readFromPokevision();
        System.out.println(pokes);

        pokes = pokes.substring(pokes.indexOf('['));

        String array[] = pokes.split(",");

        for (int i = 0; i < array.length; i = i + 8) {
            Pokemon pokemon = new Pokemon(Integer.parseInt(array[i].split(":")[1]),
                    Integer.parseInt(array[i + 3].split(":")[1]),
                    Integer.parseInt(array[i + 2].split(":")[1]),
                    (int)(System.currentTimeMillis()/1000),
                    new Coordinate(Double.parseDouble(array[i + 4].split(":")[1]),Double.parseDouble(array[i + 5].split(":")[1])));
            pokelyzer.getPokemonDatabase().addPokemon(pokemon);
        }

    }


    public String readFromPokevision() {
        HttpsURLConnection conn;
        try {
            conn = (HttpsURLConnection) (new URL("https://pokevision.com/map/data/" + Configurator.LATITUDE + "/" +  Configurator.LONGITUDE).openConnection());
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