import java.util.Scanner;

/**
 * Created by simon on 28.07.2016.
 */
public class Pokelyzer {

    private Database pokemonDatabase;
    private APIScraper apiScraper;

    public Pokelyzer() {
        this.apiScraper = new APIScraper(this);
        this.pokemonDatabase = new Database(this);
    }

    public Database getPokemonDatabase() {
        return pokemonDatabase;
    }

    public APIScraper getApiScraper() {
        return apiScraper;
    }

    public void analyse() {
        apiScraper.writePokemonToDatabase();
    }

    public static void main(String[] args) {
        Pokelyzer pokelyzer = new Pokelyzer();

        Thread scraper = new Thread(new ScraperThread(pokelyzer));
        scraper.start();

        System.out.println("Type \"end\" to end and save the project.");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if(line.equals("end")) {
            pokelyzer.getPokemonDatabase().writeDatabaseToFile();
            System.exit(0);
        }
    }

}
