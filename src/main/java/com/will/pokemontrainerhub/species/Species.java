//package com.will.pokemontrainerhub.species;
//
//import com.will.pokemontrainerhub.Enums.SpeciesName;
//import com.will.pokemontrainerhub.Enums.Type;
//
//import javax.persistence.*;
//import java.util.List;
//import java.util.Objects;
//
//@Entity(name = "species")
//@Table(name = "species")
//public class Species {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(
//            name = "id",
//            nullable = false
//    )
//    private Long id;
//    @Column(
//            name = "species_name",
//            nullable = false
//    )
//    private SpeciesName speciesName;
//
//    @OneToMany
//    private List<Type> types;
//
//    @OneToMany
//    private List<Type> weaknesses;
//
//    @Column(
//            name = "description",
//            nullable = false
//    )
//    private String description;
//}
