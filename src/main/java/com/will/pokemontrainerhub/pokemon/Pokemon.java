package com.will.pokemontrainerhub.pokemon;

import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.trainer.Trainer;

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

    @Column(name = "trainer_id")
    private Long trainerId;

    public Pokemon() {
    }

    public Pokemon(Long id, String name, Gender gender, Integer level, Double height, Double weight, Long trainerId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.level = level;
        this.height = height;
        this.weight = weight;
        this.trainerId = trainerId;
    }

    public Pokemon(String name, Gender gender, Integer level, Double height, Double weight, Long trainerId) {
        this.name = name;
        this.gender = gender;
        this.level = level;
        this.height = height;
        this.weight = weight;
        this.trainerId = trainerId;
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

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id) && Objects.equals(name, pokemon.name) && gender == pokemon.gender && Objects.equals(level, pokemon.level) && Objects.equals(height, pokemon.height) && Objects.equals(weight, pokemon.weight) && Objects.equals(trainerId, pokemon.trainerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, level, height, weight, trainerId);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", level=" + level +
                ", height=" + height +
                ", weight=" + weight +
                ", trainerId=" + trainerId +
                '}';
    }
}