package com.will.pokemontrainerhub.species;

import com.will.pokemontrainerhub.Enums.SpeciesName;
import com.will.pokemontrainerhub.Enums.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "species")
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;
    @Column(
            name = "species_name",
            nullable = false
    )
    private SpeciesName speciesName;
    @Column(
            name = "types",
            nullable = false
    )
    private List<Type> types;
    @Column(
            name = "weaknesses",
            nullable = false
    )
    private List<Type> weaknesses;
    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    public Species() {
    }

    public Species(Long id, SpeciesName speciesName, List<Type> types, List<Type> weaknesses, String description) {
        this.id = id;
        this.speciesName = speciesName;
        this.types = types;
        this.weaknesses = weaknesses;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpeciesName getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(SpeciesName speciesName) {
        this.speciesName = speciesName;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Type> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<Type> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Species species = (Species) o;
        return Objects.equals(id, species.id) && speciesName == species.speciesName && Objects.equals(types, species.types) && Objects.equals(weaknesses, species.weaknesses) && Objects.equals(description, species.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, speciesName, types, weaknesses, description);
    }

    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", speciesName=" + speciesName +
                ", types=" + types +
                ", weaknesses=" + weaknesses +
                ", description='" + description + '\'' +
                '}';
    }
}
