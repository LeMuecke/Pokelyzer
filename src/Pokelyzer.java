import java.util.Scanner;

/**
 * Created by simon on 28.07.2016.
 */
public class Pokelyzer {

    private Database pokemonDatabase;

    public Pokelyzer() {
        this.pokemonDatabase = new Database(this);
    }

    public Database getPokemonDatabase() {
        return pokemonDatabase;
    }


    public static void main(String[] args) {

        if(Configurator.LATITUDES.length != Configurator.LONGITUDES.length) {
            throw new IllegalArgumentException("Different number of Latitude/Longitude elements!");
        }

        Pokelyzer pokelyzer = new Pokelyzer();

        Thread[] scrapers = new Thread[Configurator.LATITUDES.length];
        for (int i = 0; i < Configurator.LATITUDES.length; i++) {
            scrapers[i] = new Thread(new ScraperThread(pokelyzer,Configurator.LATITUDES[i],Configurator.LONGITUDES[i]));
            scrapers[i].start();
        }

        System.out.println("Type \"end\" to end and save the project.");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if(line.equals("end")) {
            pokelyzer.getPokemonDatabase().writeDatabaseToFile();
            System.exit(0);
        }
    }

}
