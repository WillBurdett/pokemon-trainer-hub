package com.will.pokemontrainerhub.trainer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "trainer", path = "trainer")
public interface TrainerRepository extends PagingAndSortingRepository<Trainer, Long> {
}
