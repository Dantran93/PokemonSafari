package model.pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/**
 * PokemonFactory.java
 *
 * Purpose: Used to create Pokemon.
 */
public class PokemonFactory
{
    private static final int NUM_COMMON   = 6;
    private static final int NUM_UNCOMMON = 3;
    private static final int NUM_RARE     = 1;


    /**
     * getPokemon()
     *
     * Purpose: Given the rarity, a random Pokemon is created and returned.
     */
    public static Pokemon getPokemon (final Rarity rarity)
    {
        final String filename = "data/pokemon/" + (rarity == Rarity.Common ? "Common" : rarity == Rarity.Uncommon ? "Uncommon" : "Rare") + ".txt";
        final int maxLines = (rarity == Rarity.Common ? NUM_COMMON : rarity == Rarity.Uncommon ? NUM_UNCOMMON : NUM_RARE);
        final String pokemonLine = getPokemonLine(filename, maxLines);
        return createPokemon(pokemonLine.split(", "));
    } // getPokemon


    /**
     * getPokemonLine()
     *
     * Purpose: Given the file name and maximum number of lines in the file,
     *      a random line of Pokemon data from the file is returned.
     */
    private static String getPokemonLine (final String filename, final int maxLines)
    {
        final Random r = new Random();
        final int selectedPokemonID = r.nextInt(maxLines)+1;
        String selectedPokemonLine = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            for (int i = 0; i < selectedPokemonID; i++)
                selectedPokemonLine = bf.readLine();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedPokemonLine;
    } // getPokemonLine()


    /**
     * createPokemon()
     *
     * Purpose: Given an array of Pokemon data, the data is parsed, and a Pokemon
     *      object is created and returned.
     */
    private static Pokemon createPokemon (final String[] pokemonInfo)
    {
        final String name = pokemonInfo[0];
        final int hp = Integer.parseInt(pokemonInfo[1]);
        final int catchPercent = Integer.parseInt(pokemonInfo[2]);
        final int runPercent = Integer.parseInt(pokemonInfo[3]);
        final int maxDuration = Integer.parseInt(pokemonInfo[4]);
        return new Pokemon(name, hp, catchPercent, runPercent, maxDuration);
    } // createPokemon()

} // class PokemonFactory