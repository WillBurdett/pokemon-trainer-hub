package com.will.pokemontrainerhub.pokemon;

import com.will.pokemontrainerhub.trainer.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "pokemon", path = "pokemon")
public interface PokemonRepository extends PagingAndSortingRepository<Trainer, Long> {
}
