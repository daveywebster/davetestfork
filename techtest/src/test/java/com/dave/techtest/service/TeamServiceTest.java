package com.dave.techtest.service;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dave.techtest.model.Team;
import com.dave.techtest.repository.TeamRepository;
import com.dave.techtest.repository.TeamRepositoryImpl;

@RunWith(SpringRunner.class)
// @WebMvcTest(TeamController.class)
public class TeamServiceTest {
    // @Autowired
    // private MockMvc mvc;

    @Autowired
    TeamService service;

    @MockBean
    TeamRepository repository;

    @Test
    public void testShouldFindAllTeamsWhenNoSearchParametersPassed() throws Exception {

        // given
        Mockito.when(repository.getTeams()).thenReturn(TeamRepositoryImpl.teams);

        // when
        Collection<Team> teams = service.getTeams();

    }

    @Bean
    public TeamService getTeamService() {
        return new TeamServiceImpl();
    }
}
