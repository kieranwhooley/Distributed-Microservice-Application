package edu.ait.stateservice.controllers;

import edu.ait.capitalservice.dto.Capital;
import edu.ait.stateservice.dto.State;
import edu.ait.stateservice.exceptions.StateNotFoundException;
import edu.ait.stateservice.feignclients.CapitalServiceClient;
import edu.ait.stateservice.repositories.StateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class StateController {

    Logger log = LoggerFactory.getLogger(StateController.class);

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CapitalServiceClient capitalServiceClient;

    //Get all US states
    @GetMapping("/states")
    public List<State> getAllStates() {
        log.info("calling state-service to return all states");
        return stateRepository.findAll();
    }

    //Get a state and append the state capital to the response
    @GetMapping("/states/{id}")
    public State getStateById(@PathVariable int id) {
        log.info("calling capital-service to get capital info for id: " + id);
        Capital capital = capitalServiceClient.getCapitalById(id);
        log.info("calling state-service to get state info for id: " + id);
        State state = stateRepository.findById(id).get();
        state.setStateCapital(capital.getName());
        return state;
    }

    //Add a new state
    @PostMapping("/states")
    public ResponseEntity createState(@Valid @RequestBody State newState) {
        State savedState = stateRepository.save(newState);
        log.info("calling state-service to create new state");
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(savedState.getId()).toUri();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //Update an existing state or add a new one if the one being updated doesn't exist
    @PutMapping("/states")
    public ResponseEntity updateState(@Valid @RequestBody State newState) {
        if (newState.getId() != null) {
            log.info("calling state-service to create new state as id provided does not exist");
            stateRepository.save(newState);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            log.info("calling state-service to update state");
            State savedState = stateRepository.save(newState);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("{id}")
                    .buildAndExpand(savedState.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    //Delete All states
    @DeleteMapping("/states")
    public void deleteAllStates() {
        log.info("calling state-service to delete all states");
        stateRepository.deleteAll();
    }

    //Delete a specific state using the state id
    @DeleteMapping("/states/{stateId}")
    public void deleteIndividualState(@PathVariable int stateId){
        try {
            log.info("calling state-service to delete state with id of: " + stateId);
            stateRepository.deleteById(stateId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new StateNotFoundException("Unable to delete state with Id " + stateId + " as it does not exist in the database");
        }
    }

    //Derived query to get all states with a population greater than a specified amount
    @GetMapping("/states/population")
    public List<State> getStatesWithPopulationGreaterThan(@RequestParam(value = "populationOver") int population) {
        log.info("calling state-service to return states with population over: " + population);
        return stateRepository.findByPopulationGreaterThan(population);
    }

    //Derived query to get all states with a specified piece of text in their name
    @GetMapping("/states/name")
    public List<State> getStatesWithNameContaining(@RequestParam(value = "contains") String name) {
        log.info("calling state-service to return states which contain: " + name);
        return stateRepository.findByNameContainingIgnoreCase(name);
    }

    //Derived query to get all states within a specific timezone
    @GetMapping("/states/timezone")
    public List<State> getStatesWithSpecificTimezone(@RequestParam(value = "zone") String timezone) {
        log.info("calling state-service to return states which have timezone of: " + timezone);
        return stateRepository.findByTimezoneIgnoreCase(timezone);
    }

    //Derived query to get all states founded before a specific date
    @GetMapping("/states/foundedBefore")
    public List<State> getStatesFoundedBefore(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate foundedDate) {
        log.info("calling state-service to return states founded before: " + foundedDate);
        return stateRepository.findByFoundedBefore(foundedDate);
    }

    //Derived query to get all states founded after a specific date
    @GetMapping("/states/foundedAfter")
    public List<State> getStatesFoundedAfter(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate foundedDate) {
        log.info("calling state-service to return states founded after: " + foundedDate);
        return stateRepository.findByFoundedAfter(foundedDate);
    }

}
