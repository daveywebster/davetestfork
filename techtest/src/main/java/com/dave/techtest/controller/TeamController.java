package com.dave.techtest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dave.techtest.model.Team;
import com.dave.techtest.service.TeamService;

@Controller
@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/teams")
    public ResponseEntity<Collection<Team>> getAllEmployees() {

        Collection<Team> teams = teamService.getTeams();

        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

}
