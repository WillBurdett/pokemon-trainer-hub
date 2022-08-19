package com.will.pokemontrainerhub.utils;

import com.will.pokemontrainerhub.pokemon.Pokemon;
import com.will.pokemontrainerhub.trainer.Trainer;

import java.util.ArrayList;

public class ConvertCollections {
    public static ArrayList<Pokemon> pokemonToArrayList(Iterable<Pokemon> list){
        ArrayList<Pokemon> result = new ArrayList<>();
        for (Pokemon p : list){
            result.add(p);
        }
        return result;
    }
    public static ArrayList<Trainer> trainerToArrayList(Iterable<Trainer> list){
        ArrayList<Trainer> result = new ArrayList<>();
        for (Trainer t : list){
            result.add(t);
        }
        return result;
    }
}
