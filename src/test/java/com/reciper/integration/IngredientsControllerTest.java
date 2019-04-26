package com.reciper.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reciper.entity.Ingredient;
import com.reciper.util.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class IngredientsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @Test
    public void positiveTestCreate() throws Exception {
        Ingredient ingredient = new Ingredient();

        String generatedString = TestUtils.generateRandomLettersString(10);
        ingredient.setName(generatedString);
        ingredient.setPicture("/path/to/picture");

        mockMvc.perform(
                post("/ingredients").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredient)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.picture").value("/path/to/picture"))
                .andExpect(jsonPath("$.name").value(generatedString));
    }

    @Test
    public void negativeTestCreate_setNameAsNull() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(null);
        ingredient.setPicture("/path/to/picture");

        mockMvc.perform(
                post("/ingredients").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredient)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void negativeTestCreate_setNameOverMaxSize() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(TestUtils.generateRandomLettersString(121));
        ingredient.setPicture("/path/to/picture");

        mockMvc.perform(
                post("/ingredients").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ingredient)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void positiveTestRead() throws Exception {
        mockMvc.perform(get("/ingredients"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void positiveTestReadOne() throws Exception {
        Ingredient ingredient = new Ingredient();
        String generatedString = TestUtils.generateRandomLettersString(10);
        ingredient.setName(generatedString);
        ingredient.setPicture("/path/to/picture");

        RestTemplate restTemplate = new RestTemplate();
        Ingredient posted = restTemplate.postForObject("http://localhost:" + port + "/ingredients", ingredient, Ingredient.class);

        Assert.assertNotNull(posted);

        mockMvc.perform(get("/ingredients/" + posted.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(posted.getId()))
                .andExpect(jsonPath("$.name").value(posted.getName()))
                .andExpect(jsonPath("$.picture").value(posted.getPicture()));
    }

    @Test
    public void positiveTestUpdate() throws Exception {
        Ingredient ingredient = new Ingredient();

        String generatedString = TestUtils.generateRandomLettersString(10);
        ingredient.setName(generatedString);
        ingredient.setPicture("/path/to/picture");

        RestTemplate restTemplate = new RestTemplate();
        Ingredient posted = restTemplate.postForObject("http://localhost:" + port + "/ingredients", ingredient, Ingredient.class);

        Assert.assertNotNull(posted);

        posted.setName(TestUtils.generateRandomLettersString(12));

        mockMvc.perform(put("/ingredients/" + posted.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(posted))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(posted.getId()))
                .andExpect(jsonPath("$.name").value(posted.getName()))
                .andExpect(jsonPath("$.picture").value(posted.getPicture()));
    }

    @Test
    public void negativeTestUpdate_setNameAsNull() throws Exception {
        Ingredient ingredient = new Ingredient();

        String generatedString = TestUtils.generateRandomLettersString(10);
        ingredient.setName(generatedString);
        ingredient.setPicture("/path/to/picture");

        RestTemplate restTemplate = new RestTemplate();
        Ingredient posted = restTemplate.postForObject("http://localhost:" + port + "/ingredients", ingredient, Ingredient.class);

        Assert.assertNotNull(posted);

        posted.setName(null);

        mockMvc.perform(put("/ingredients/" + posted.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(posted))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void negativeTestUpdate_setNameOverMaxSize() throws Exception {
        Ingredient ingredient = new Ingredient();

        String generatedString = TestUtils.generateRandomLettersString(10);
        ingredient.setName(generatedString);
        ingredient.setPicture("/path/to/picture");

        RestTemplate restTemplate = new RestTemplate();
        Ingredient posted = restTemplate.postForObject("http://localhost:" + port + "/ingredients", ingredient, Ingredient.class);

        Assert.assertNotNull(posted);

        posted.setName(TestUtils.generateRandomLettersString(121));

        mockMvc.perform(put("/ingredients/" + posted.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(posted))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void positiveTestDelete() throws Exception {
        Ingredient ingredient = new Ingredient();

        String generatedString = TestUtils.generateRandomLettersString(10);
        ingredient.setName(generatedString);
        ingredient.setPicture("/path/to/picture");

        RestTemplate restTemplate = new RestTemplate();
        Ingredient posted = restTemplate.postForObject("http://localhost:" + port + "/ingredients", ingredient, Ingredient.class);

        Assert.assertNotNull(posted);

        mockMvc.perform(delete("/ingredients/" + posted.getId())).andExpect(status().isOk());
    }

    @Test
    public void negativeTestDelete_deleteNonExisting() throws Exception {
        mockMvc.perform(delete("/ingredients/" + new Random().nextInt(100000) + 100000)).andExpect(status().isBadRequest());
    }
}
