package edu.ait.clientfeign.feignclients;

import edu.ait.stateservice.dto.State;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("zuul-api-gateway")
public interface StateServiceClient {

    //Get state using state id
    @GetMapping("/state-service/states/{id}")
    State getStateById(@PathVariable int id);

    //Get all states
    @GetMapping("/state-service/states")
    List<State> getAllStates();

    //Create a new state
    @PostMapping("/state-service/states")
    ResponseEntity createState(@Valid @RequestBody State newState);

    //Delete a state
    @DeleteMapping("/state-service/states/{stateId}")
    void deleteIndividualState(@PathVariable int stateId);

    //Derived query to get all states within a specific timezone
    @GetMapping("/state-service/states/timezone")
    List<State> getStatesWithSpecificTimezone(@RequestParam(value = "zone") String timezone);


}
