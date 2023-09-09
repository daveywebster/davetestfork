package com.dave.techtest.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dave.techtest.model.Team;
import com.dave.techtest.repository.TeamRepository;
import com.dave.techtest.repository.TeamRepositoryImpl;
import com.dave.techtest.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamController.class)
public class TeamControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeamService service;

    @MockBean
    TeamRepository repository;

    @Test
    public void testShouldFindAllTeamsWhenNoSearchParametersPassed() throws Exception {

        // given
        Team t1 = new Team();
        Team t2 = new Team();
        Team t3 = new Team();

        List<Team> teams = new ArrayList<Team>();
        Collections.addAll(teams, t1, t2, t3);

        Mockito.when(service.getTeams()).thenReturn(TeamRepositoryImpl.teams);

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
}
