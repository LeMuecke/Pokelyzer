/**
 * Created by simon on 28.07.2016.
 */
public class Pokemon {

    private int pokedexNumber;
    private int despawnTimestamp;
    private int creationTimestamp;      //the time the programm pulled from pokevision
    private Coordinate coordinate;

    public Pokemon(int pokedexNumber, int despawnTimestamp, int creationTimestamp, Coordinate coordinate) {
        this.pokedexNumber = pokedexNumber;
        this.despawnTimestamp = despawnTimestamp;
        this.creationTimestamp = creationTimestamp;
        this.coordinate = coordinate;
    }

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public int getDespawnTimestamp() {
        return despawnTimestamp;
    }

    public int getCreationTimestamp() {
        return creationTimestamp;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
