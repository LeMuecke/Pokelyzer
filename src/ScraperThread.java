/**
 * Created by simon on 28.07.2016.
 */
public class ScraperThread implements Runnable {

    Pokelyzer pokelyzer;
    APIScraper apiScraper;

    public ScraperThread(Pokelyzer pokelyzer) {
        this.pokelyzer = pokelyzer;
        apiScraper = new APIScraper(pokelyzer);
    }

    @Override
    public void run() {
        while(true) {
            apiScraper.writePokemonToDatabase();
            System.out.println("New batch of Pokemon just arrived ;)");
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                System.out.println("The 60s sleep of the ScraperThread got interrupted!");
                e.printStackTrace();
            }
        }
    }
}
