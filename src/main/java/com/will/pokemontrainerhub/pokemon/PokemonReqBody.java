package com.will.pokemontrainerhub.pokemon;

import com.will.pokemontrainerhub.Enums.Gender;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class PokemonReqBody {
    @NotEmpty
    @Size(min = 2, message = "name must have at least 2 characters")
    private String name;
    @NotEmpty
    @Size(min = 4, message = "name must be MALE, FEMALE or OTHER")
    private Gender gender;
    @NotEmpty
    @Min(value = 1)
    @Max(value = 100)
    private Integer level;
    @NotEmpty
    @Min(value = 1)
    private Double height;
    @NotEmpty
    @Min(value = 1)
    private Double weight;

    public PokemonReqBody() {
    }

    public PokemonReqBody(String name, Gender gender, Integer level, Double height, Double weight) {
        this.name = name;
        this.gender = gender;
        this.level = level;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        PokemonReqBody that = (PokemonReqBody) o;
        return Objects.equals(name, that.name) && gender == that.gender && Objects.equals(level, that.level) && Objects.equals(height, that.height) && Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, level, height, weight);
    }

    @Override
    public String toString() {
        return "PokemonReqBody{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", level=" + level +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
