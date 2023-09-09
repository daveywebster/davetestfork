package com.dave.techtest.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dave.techtest.model.SPORT;
import com.dave.techtest.model.Team;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringRunner.class)
// @WebMvcTest(TeamController.class)
// @AutoConfigureMockMvc
// @SpringBootTest
public class TeamControllerTest extends AbstractTest {

    // @Autowired
    // private MockMvc mvc;


    // private MockMvc mvc =
    // MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    // @MockBean
    // private TeamService service;

    // @MockBean
    // private TeamRepository repository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testReturnAllTeams() throws Exception {

        // given - loading the whole app context and actually executing all
        // code!
        // Mockito.when(service.getTeams()).thenReturn(TeamRepositoryImpl.teams);
        // Mockito.when(repository.getTeams()).thenReturn(TeamRepositoryImpl.teams);

        // when
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get("/teams").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        // then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(content);

        ObjectMapper objectMapper = new ObjectMapper();
        Team[] team_arr = objectMapper.readValue(content, Team[].class);

        assertEquals(4, team_arr.length);
    }

    @Test
    public void testAddsNewTeam() throws Exception {
        String uri = "/teams";
        Team team = new Team();
        team.setId("502");
        team.setName("Yankees");
        team.setSport(SPORT.BASEBALL);
        team.setLeague("NBL");
        String inputJson = super.mapToJson(team);
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

}
