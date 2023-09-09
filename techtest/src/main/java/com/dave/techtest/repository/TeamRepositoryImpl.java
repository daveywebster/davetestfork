package com.dave.techtest.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Repository;

import com.dave.techtest.exception.DuplicateTeamException;
import com.dave.techtest.exception.TeamNotFoundException;
import com.dave.techtest.model.SPORT;
import com.dave.techtest.model.Team;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    private final Lock lock = new ReentrantLock();

    // private static List<Team> teams = new ArrayList<Team>();

    public static List<Team> teams = new ArrayList<Team>();

    static {

        Team team3 = new Team();
        team3.setId("300");
        team3.setName("Arsenal");
        team3.setSport(SPORT.FOOTBALL);
        team3.setLeague("Premiership");
        teams.add(team3);

        Team team1 = new Team();
        team1.setId("100");
        team1.setName("Oilers");
        team1.setSport(SPORT.HOCKEY);
        team1.setLeague("NHL");
        teams.add(team1);

        Team team2 = new Team();
        team2.setId("101");
        team2.setName("Flames");
        team2.setSport(SPORT.HOCKEY);
        team2.setLeague("NHL");
        teams.add(team2);

        Team team4 = new Team();
        team4.setId("500");
        team4.setName("Blue Jays");
        team4.setSport(SPORT.BASEBALL);
        team4.setLeague("NBL");
        teams.add(team4);

    }

    @Override
    public Collection<Team> getTeams() {
        List<Team> teamList = new ArrayList<Team>(teams);
        Collections.sort(teamList);
        return teamList;
    }

    @Override
    public Team addTeam(Team team) {
        lock.lock();
        try {
            if (findTeamById(team.getId()) != null) {
                throw new DuplicateTeamException("Team with id [" + team.getId() + "] already exists.");
            }
            teams.add(team);
        } finally {
            lock.unlock();
        }

        return team;
    }

    @Override
    public void deleteTeam(String id) {
        lock.lock();
        try {
            if (findTeamById(id) == null) {
                throw new TeamNotFoundException("Team with id [" + id + "] does not exist.");
            }
            deleteTeamById(id);
        } finally {
            lock.unlock();
        }
    }
    
    @Override
    public Team updateTeam(String id, Team team) {

        lock.lock();
        try {
            deleteTeam(id);
            addTeam(team);
        } finally {
            lock.unlock();
        }

        return team;
    }

    private void deleteTeamById(String id) {

        Iterator<Team> iterator = teams.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
            }
        }
    }
    
    private Team findTeamByName(String name) {
        Team team = teams.stream().filter(t -> name.equals(t.getName())).findAny().orElse(null);
        return team;
    }

    private Team findTeamById(String id) {
        Team team = teams.stream().filter(t -> id.equals(t.getId())).findAny().orElse(null);
        return team;
    }

}
