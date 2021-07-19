package edu.ait.capitalservice.controllers;

import edu.ait.capitalservice.dto.Capital;
import edu.ait.capitalservice.exceptions.CapitalNotFoundException;
import edu.ait.capitalservice.repositories.CapitalRepository;
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
public class CapitalController {

    Logger log = LoggerFactory.getLogger(CapitalController.class);

    @Autowired
    CapitalRepository capitalRepository;

    //Get all state capitals
    @GetMapping("/capitals")
    public List<Capital> getAllCapitals() {
        log.info("calling capital-service to return all capitals");
        return capitalRepository.findAll();
    }

    //Get a specific US state capital using the state capital id
    @GetMapping("/capitals/{capitalId}")
    public Capital getCapitalById(@PathVariable int capitalId) {
        log.info("calling capital-service to return capital information for specific id");
        Optional<Capital> foundCapital = capitalRepository.findById(capitalId);
        if (foundCapital.isPresent())
            return foundCapital.get();
        else
            throw new CapitalNotFoundException("The state capital Id entered does not exist in the database");
    }

    //Add a new state capital
    @PostMapping("/capitals")
    public ResponseEntity createCapital(@Valid @RequestBody Capital newCapital) {
        log.info("calling capital-service to create a new capital");
        Capital savedCapital = capitalRepository.save(newCapital);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(savedCapital.getId()).toUri();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //Update an existing state capital or add a new one if the one being updated doesn't exist
    @PutMapping("/capitals")
    public ResponseEntity updateCapital(@Valid @RequestBody Capital newCapital) {
        if (newCapital.getId() != null) {
            log.info("calling capital-service to create a new capital as id entered does not exist");
            capitalRepository.save(newCapital);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            log.info("calling capital-service to update existing capital");
            Capital savedCapital = capitalRepository.save(newCapital);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("{id}")
                    .buildAndExpand(savedCapital.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    //Delete All state capitals
    @DeleteMapping("/capitals")
    public void deleteAllCapitals() {
        log.info("calling capital-service to delete all capitals");
        capitalRepository.deleteAll();
    }

    //Delete a specific state capital using the state capital id
    @DeleteMapping("/capitals/{capitalId}")
    public void deleteIndividualCapital(@PathVariable int capitalId){
        try {
            log.info("calling capital-service to delete specific capital with id of: " + capitalId);
            capitalRepository.deleteById(capitalId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new CapitalNotFoundException("Unable to delete state capital with Id " + capitalId + " as it does not exist in the database");
        }
    }

    //Derived query to get all state capitals with a population greater than a specified amount
    @GetMapping("/capitals/population")
    public List<Capital> getCapitalsWithPopulationGreaterThan(@RequestParam(value = "populationOver") int population) {
        log.info("calling capital-service to return all capitals with a population over: " + population);
        return capitalRepository.findByPopulationGreaterThan(population);
    }

    //Derived query to get all state capitals incorporated before a specific date
    @GetMapping("/capitals/incorporatedBefore")
    public List<Capital> getCapitalsIncorporatedBefore(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate incorporatedDate) {
        log.info("calling capital-service to return all capitals incorporated before: " + incorporatedDate);
        return capitalRepository.findByIncorporatedBefore(incorporatedDate);
    }

    //Derived query to get all state capitals incorporated after a specific date
    @GetMapping("/capitals/incorporatedAfter")
    public List<Capital> getCapitalsIncorporatedAfter(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate incorporatedDate) {
        log.info("calling capital-service to return all capitals incorporated after: " + incorporatedDate);
        return capitalRepository.findByIncorporatedAfter(incorporatedDate);
    }

    //Derived query to get all state capitals that have a demonym starting with a specific letter
    @GetMapping("/capitals/demonym")
    public List<Capital> getCapitalsWithDemonymStartingWith(@RequestParam(value = "startsWith") String character) {
        log.info("calling capital-service to return all capitals with a demonym beginning with: " + character);
        return capitalRepository.findByDemonymStartingWithIgnoreCase(character);
    }
}
