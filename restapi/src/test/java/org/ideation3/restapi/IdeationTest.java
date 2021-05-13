package org.ideation3.restapi;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.ideation3.restapi.model.Ideation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class IdeationTest {
    @Autowired
    private MockMvc mockMvc;
  
    @Autowired
    private ObjectMapper mapper;
  
    @Test
    @Order(1)
    public void canAddIdea() throws JsonProcessingException, Exception {
        Ideation ideation = Ideation.
          .idea_no("ABC")
          .topic("123")
          .description("XXXXXX")
            .build();
        this.mockMvc
          .perform(post("/repo/ideation/register-product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(ideation))
            .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andDo(print())
          .andExpect(content().string(containsString("ABC")));
      }

      @Test
      @Order(2)
      public void canViewIdea() throws JsonProcessingException, Exception {
          this.mockMvc
            .perform(get("/repo/ideation/view/1"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(containsString("ABC")));
        }

        @Test
        @Order(3)
        public void canEditIdea() throws JsonProcessingException, Exception {
          Ideation ideation = Ideation.builder()
            .idea_no("ABC")
            .topic("123")
            .description("XXXXXX")
              .build();
          this.mockMvc
            .perform(put("/repo/ideation/update/1")
              .contentType(MediaType.APPLICATION_JSON)
              .content(this.mapper.writeValueAsString(ideation))
              .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(containsString("XYZ")));
        }
      
        @Test
        @Order(4)
        public void canSearchIdea() throws JsonProcessingException, Exception {
          this.mockMvc
            .perform(get("/repo/ideation/search/XYZ"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(containsString("XYZ")));
          this.mockMvc
            .perform(get("/repo/ideation/search/123"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(containsString("123")));
        }
      
        @Test
        @Order(5)
        public void canGetAll() throws JsonProcessingException, Exception {
          this.mockMvc
            .perform(get("/repo/ideation/get-all"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(containsString("XYZ")));
        }
      
        @Test
        @Order(6)
        public void canDeleteIdea() throws JsonProcessingException, Exception {
          this.mockMvc
            .perform(delete("/repo/ideation/delete/1"))
            .andExpect(status().isOk())
            .andDo(print())
            .andExpect(content().string(containsString("XYZ")));
        }
}
