package com.dave.techtest.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dave.techtest.model.Team;
import com.dave.techtest.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    public TeamServiceImpl() {

    }
    @Override
    public Collection<Team> getTeams() {
        return teamRepository.getTeams();
    }

    @Override
    public Team addTeam(Team team) {
        return teamRepository.addTeam(team);
    }

    @Override
    public void deleteTeam(String id) {
        teamRepository.deleteTeam(id);
    }

    @Override
    public Team updateTeam(String id, Team team) {
        return teamRepository.updateTeam(id, team);
    }

}
