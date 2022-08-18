package com.will.pokemontrainerhub.utils;

import com.will.pokemontrainerhub.pokemon.Pokemon;

import java.util.ArrayList;

public class ConvertCollections {
    public static ArrayList<Pokemon> pokemonToArrayList(Iterable<Pokemon> list){
        ArrayList<Pokemon> result = new ArrayList<>();
        for (Pokemon p : list){
            result.add(p);
        }
        return result;
    }
}
