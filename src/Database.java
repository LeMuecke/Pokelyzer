import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by simon on 28.07.2016.
 */
public class Database {

    private ArrayList<Pokemon> pokeList = new ArrayList<>();
    private Pokelyzer pokelyzer;

    public Database(Pokelyzer pokelyzer) {
        this.pokelyzer = pokelyzer;
    }

    public void addPokemon(Pokemon pokemon) {
        if(!pokeList.contains(pokemon)) {
            pokeList.add(pokemon);
        }
    }

    public boolean removePokemon(Pokemon pokemon) {
        return pokeList.remove(pokemon);
    }

    public boolean hasPokemon(Pokemon pokemon) {
        return pokeList.contains(pokemon);
    }

    public Pokemon getPokemon(int index) {
        return pokeList.get(index);
    }

    public void writeDatabaseToFile() {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        Document doc = null;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.newDocument();

            Element mainRootElement = doc.createElementNS("Pokemons", "Pokemons");
            doc.appendChild(mainRootElement);

            for (int i = 0; i < pokeList.size(); i++) {
                mainRootElement.appendChild(getPokemon(doc, String.valueOf(i), pokeList.get(i)));
            }

        }
        catch(Exception e) {
            System.out.println("Converting the Pokemon Database to XML failed!");
        }

        TransformerFactory transformerFactory =
                TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e1) {
            e1.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result =
                new StreamResult(new File(Configurator.FILE_LOCATION));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e1) {
            e1.printStackTrace();
        }

    }

    public void readDatabaseFromFile() {

    }


    //xml helpers
    private static Node getPokemon(Document doc, String id, Pokemon pokemon) {
        Element pokemonElement = doc.createElement("Pokemon");
        pokemonElement.setAttribute("id", id);
        pokemonElement.appendChild(getPokemonElements(doc, pokemonElement, "pokeId", String.valueOf(pokemon.getId())));
        pokemonElement.appendChild(getPokemonElements(doc, pokemonElement, "pdN", String.valueOf(pokemon.getPokedexNumber())));
        pokemonElement.appendChild(getPokemonElements(doc, pokemonElement, "dsT", String.valueOf(pokemon.getDespawnTimestamp())));
        pokemonElement.appendChild(getPokemonElements(doc, pokemonElement, "cT", String.valueOf(pokemon.getCreationTimestamp())));
        pokemonElement.appendChild(getPokemonElements(doc, pokemonElement, "ltt", String.valueOf(pokemon.getCoordinate().getLatitude())));
        pokemonElement.appendChild(getPokemonElements(doc, pokemonElement, "lgt", String.valueOf(pokemon.getCoordinate().getLongitude())));
        return pokemonElement;
    }

    private static Node getPokemonElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }


}
