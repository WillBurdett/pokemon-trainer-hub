package com.will.pokemontrainerhub.trainer.unittests;

import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerController;
import com.will.pokemontrainerhub.trainer.TrainerReqBody;
import com.will.pokemontrainerhub.trainer.TrainerService;
import com.will.pokemontrainerhub.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrainerController.class)
class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainerService service;

    @Test
    void getAllTrainers() throws Exception {
        Trainer bob = new Trainer(1L, "bob", 2, Gender.MALE, new ArrayList<>());
        when(service.getAllTrainers()).thenReturn(Arrays.asList(bob));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/trainer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("bob")))
                .andExpect(jsonPath("$[0].age", is(2)))
                .andExpect(jsonPath("$[0].pokemon", hasSize(0)))
                .andExpect(jsonPath("$[0].gender", is("MALE")));

        verify(service, times(1)).getAllTrainers();
    }

    @Test
    void getTrainerById() throws Exception {
        Trainer bob = new Trainer(1L, "bob", 2, Gender.MALE, new ArrayList<>());
        when(service.getTrainerById(1L)).thenReturn(Optional.of(bob));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/trainer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("bob")))
                .andExpect(jsonPath("$.age", is(2)))
                .andExpect(jsonPath("$.pokemon", hasSize(0)))
                .andExpect(jsonPath("$.gender", is("MALE")));

        verify(service, times(1)).getTrainerById(1L);
    }

    @Test
    void addTrainer() throws Exception {
        TrainerReqBody bob = new TrainerReqBody("bob", 2, Gender.MALE);
        mockMvc.perform(post("/trainer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(bob)))
                .andExpect(status().isOk());

        verify(service, times(1)).addTrainer(bob);
    }

    @Test
    void deleteTrainerById() throws Exception {
        mockMvc.perform(delete("/trainer/1"));
        verify(service, times(1)).deleteTrainerById(1L);
    }

    @Test
    void updateNonPokemonTrainerInfo() throws Exception {
        TrainerReqBody bob = new TrainerReqBody ("bob", 2, Gender.MALE);
        mockMvc.perform(MockMvcRequestBuilders.put("/trainer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(bob)))
                .andExpect(status().isOk());
        verify(service, times(1)).updateTrainerById(1L, bob);
    }

    @Test
    void addPokemonToTrainer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/trainer/1/add-pokemon/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).addPokemonToTrainer(1L, "1");
    }

    @Test
    void removePokemonFromTrainer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/trainer/1/remove-pokemon/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
        verify(service, times(1)).removePokemonFromTrainer(1L, "1");
    }

    @Test
    void removeAllPokemonFromTrainer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/trainer/1/remove-all-pokemon")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
        verify(service, times(1)).removeAllPokemonFromTrainer(1L);
    }
}