package com.dave.techtest.service;

import java.util.Collection;

import com.dave.techtest.model.Team;

public interface TeamService {

    public Collection<Team> getTeams();

    public Team addTeam(Team team);

    public void deleteTeam(String id);

    public Team updateTeam(String id, Team team);

}
