package br.com.wiliansanello.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wiliansanello.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.wiliansanello.gestao_vagas.utils.TestUtils;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }

    @Test
    public void shouldBeAbleToCreateANewJob() throws Exception {

        var createdJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
            TestUtils.objectToJSON(createdJobDTO))
            .header("Authorization", 
                TestUtils.generateToken(UUID.fromString("173124ba-62e2-4719-8481-c1d5c774e8cc"), 
                    "JAVAGAS_@123#")))
        .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }    
    
}
