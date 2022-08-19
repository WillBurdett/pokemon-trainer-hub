package com.will.pokemontrainerhub.trainer.integrationtests;

import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerController;
import com.will.pokemontrainerhub.trainer.TrainerRepository;
import com.will.pokemontrainerhub.utils.ConvertCollections;
import com.will.pokemontrainerhub.utils.JsonUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TrainerControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TrainerController trainerController;

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private TrainerRepository trainerRepository;

    @After
    public void tearDown() {
        trainerRepository.deleteAll();
    }

    @Test
    public void getsAllTrainer() throws Exception {
        // given
        Trainer bob = new Trainer("bob", 2, Gender.MALE);
        entityManager.persist(bob);
        entityManager.flush();
        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get("/trainer")
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("bob")))
                .andExpect(jsonPath("$[0].age", is(2)))
                .andExpect(jsonPath("$[0].gender", is("MALE")))
                .andExpect(jsonPath("$[0].pokemon", is(new ArrayList())));
    }

    @Test
    public void getTrainerById() throws Exception {
        // given
        Trainer bob = new Trainer("bob", 2, Gender.MALE);
        entityManager.persist(bob);
        entityManager.flush();
        // when
        this.mockMvc.perform(MockMvcRequestBuilders.get("/trainer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("bob")))
                .andExpect(jsonPath("$.age", is(2)))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.pokemon", is(new ArrayList())));
    }

//    @Test
//    public void getTrainerByName_ReturnsTrainerWhenExists() throws Exception {
//        // given
//        Trainer bob = new Trainer("bob", 2, Gender.MALE);
//        entityManager.persist(bob);
//        entityManager.flush();
//
//        // when
//        mockMvc.perform(MockMvcRequestBuilders.get("/Trainer/bob")
//                        .contentType(MediaType.APPLICATION_JSON))
//                // then
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name", is("bob")))
//                .andExpect(jsonPath("$.legs", is(2)))
//                .andExpect(jsonPath("$.canFly", is(false)));
//    }
//
//    @Test
//    public void getTrainerByName_BadRequestWhenTrainerDoesNotExist() throws Exception {
//        // when
//        mockMvc.perform(MockMvcRequestBuilders.get("/Trainer/bob")
//                        .contentType(MediaType.APPLICATION_JSON))
//                // then
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void addsTrainer_WhenReqOK() throws Exception {
//        // given
//        Trainer bob = new Trainer("bob", 2, Gender.MALE);
//        entityManager.persist(bob);
//        entityManager.flush();
//
//        // when
//        mockMvc.perform(MockMvcRequestBuilders.get("/Trainer/bob")
//                        .contentType(MediaType.APPLICATION_JSON))
//                // then
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name", is("bob")))
//                .andExpect(jsonPath("$.legs", is(2)))
//                .andExpect(jsonPath("$.canFly", is(false)));
//    }
//
//    @Test
//    public void updatesTrainerByName_WhenTrainerExists() throws Exception {
//        // given
//        Trainer bob = new Trainer("bob", 2, Gender.MALE);
//        Trainer bobUpdated = new Trainer("bob", 10, Gender.MALE);
//        entityManager.persist(bob);
//        entityManager.flush();
//
//        // when
//        mockMvc.perform(MockMvcRequestBuilders.put("/Trainer/bob")
//                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bobUpdated)));
//
//        // then
//        mockMvc.perform(MockMvcRequestBuilders.get("/Trainer/bob")
//                        .contentType(MediaType.APPLICATION_JSON))
//                // then
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.name", is("bob")))
//                .andExpect(jsonPath("$.legs", is(10)))
//                .andExpect(jsonPath("$.canFly", is(true)));
//    }
//
//    @Test
//    public void updatesTrainerByName_BadRequestWhenTrainerDoesNotExist() throws Exception {
//        // given
//        Trainer bob = new Trainer("bob", 2, Gender.MALE);
//        // when
//        mockMvc.perform(MockMvcRequestBuilders.put("/Trainer/bob")
//                        .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bob)))
//                // then
//                .andExpect(status().isBadRequest());
//    }
//
//
//    @Test
//    public void deletesTrainerByName_WhenTrainerExists() throws Exception {
//        // given
//        Trainer bob = new Trainer("bob", 2, Gender.MALE);
//        entityManager.persist(bob);
//        entityManager.flush();
//
//        // when
//        mockMvc.perform(MockMvcRequestBuilders.delete("/Trainer/bob")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/Trainer/bob")
//                        .contentType(MediaType.APPLICATION_JSON))
//                // then
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void deleteTrainerByName_BadRequestWhenTrainerDoesNotExist() throws Exception {
//        // when
//        mockMvc.perform(MockMvcRequestBuilders.delete("/Trainer/bob")
//                        .contentType(MediaType.APPLICATION_JSON))
//                // then
//                .andExpect(status().isBadRequest());
//    }
}