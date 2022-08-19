package com.will.pokemontrainerhub.trainer;

import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.pokemon.Pokemon;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "trainer")
@Table(name = "trainer")
public class Trainer {
    @Id
    @SequenceGenerator(
            name = "trainer_sequence",
            sequenceName = "trainer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trainer_sequence"
    )
    @Column(
            name = "trainer_id",
            nullable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "age",
            nullable = false
    )
    private Integer age;
    @Column(
            name = "gender",
            nullable = false
    )
    private Gender gender;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "trainer_id", referencedColumnName = "trainer_id")
    private List<Pokemon> pokemon;

    public Trainer() {
    }

    public Trainer(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pokemon = new ArrayList<>();
    }

    public Trainer(Long id, String name, Integer age, Gender gender, List<Pokemon> pokemon) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.pokemon = pokemon;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(id, trainer.id) && Objects.equals(name, trainer.name) && Objects.equals(age, trainer.age) && gender == trainer.gender && Objects.equals(pokemon, trainer.pokemon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender, pokemon);
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", pokemon=" + pokemon +
                '}';
    }
}

