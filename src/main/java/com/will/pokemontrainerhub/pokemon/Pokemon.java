package com.will.pokemontrainerhub.pokemon;

import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.species.Species;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "pokemon")
@Table(name = "pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "species",
            nullable = false
    )
    private Species species;
    @Column(
            name = "gender",
            nullable = false
    )
    private Gender gender;
    @Column(
            name = "level",
            nullable = false
    )
    private Integer level;
    @Column(
            name = "height",
            nullable = false
    )
    private Double height;
    @Column(
            name = "weight",
            nullable = false
    )
    private Double weight;

    public Pokemon() {
    }

    public Pokemon(String name, Species species, Gender gender, Integer level, Double height, Double weight) {
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.level = level;
        this.height = height;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id) && Objects.equals(name, pokemon.name) && Objects.equals(species, pokemon.species) && gender == pokemon.gender && Objects.equals(level, pokemon.level) && Objects.equals(height, pokemon.height) && Objects.equals(weight, pokemon.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, species, gender, level, height, weight);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species=" + species +
                ", gender=" + gender +
                ", level=" + level +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
