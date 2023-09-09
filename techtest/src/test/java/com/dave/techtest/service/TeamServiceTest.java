package com.dave.techtest.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dave.techtest.model.Team;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceTest {

    @Autowired
    TeamService service;

    @Test
    public void testShouldReturnAllTeams() throws Exception {

        // given
        // Mockito.when(repository.getTeams()).thenReturn(TeamRepositoryImpl.teams);

        // when
        Collection<Team> teams = service.getTeams();
        
        ArrayList<Team> newList = new ArrayList<>(teams);

        assertEquals(4, newList.size());
        System.out.println(newList.toString());
    }

    // @Bean
    // public TeamService getTeamService() {
    // return new TeamServiceImpl();
    // }
}
