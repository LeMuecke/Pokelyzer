/**
 * Created by simon on 28.07.2016.
 */
public class Pokemon {

    private int id;
    private int pokedexNumber;
    private int despawnTimestamp;
    private int creationTimestamp;      //the time the programm pulled from pokevision
    private Coordinate coordinate;

    public Pokemon(int id, int pokedexNumber, int despawnTimestamp, int creationTimestamp, Coordinate coordinate) {
        this.id = id;
        this.pokedexNumber = pokedexNumber;
        this.despawnTimestamp = despawnTimestamp;
        this.creationTimestamp = creationTimestamp;
        this.coordinate = coordinate;
    }

    public int getId() {
        return id;
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
