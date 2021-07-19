package edu.ait.clientfeign.controllers;

import edu.ait.clientfeign.ConfigParams;
import edu.ait.clientfeign.feignclients.StateServiceClient;
import edu.ait.stateservice.dto.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RefreshScope
@RestController
public class ClientFeignController {

    @Autowired
    StateServiceClient stateServiceClient;

    //Cloud Config parameters
    @Value("${client-feign.configName}")
    private String configName;
    @Value("${client-feign.configInfo}")
    private String configInfo;

    //Get config parameters from the Cloud Config Server
    @GetMapping("client-feign/configParams")
    public ConfigParams getConfigParamsFromCloudConfigServer() {
        return new ConfigParams(configName, configInfo);
    }

    //Get state using state id - The returned value also includes the state capital from the capital service
    @GetMapping("client-feign/states/{id}")
    public State getStateFromStateService(@PathVariable int id) {
        return stateServiceClient.getStateById(id);
    }

    //Get all states
    @GetMapping("client-feign/states")
    List<State> getAllStates() {
        return stateServiceClient.getAllStates();
    }

    //Create a new state
    @PostMapping("/client-feign/states")
    ResponseEntity createState(@Valid @RequestBody State newState) {
        return stateServiceClient.createState(newState);
    }

    //Delete a state
    @DeleteMapping("/client-feign/states/{stateId}")
    void deleteIndividualState(@PathVariable int stateId) {
        stateServiceClient.deleteIndividualState(stateId);
    }

    //Derived query to get states in a specific timezone
    @GetMapping("/client-feign/states/timezone")
    List<State> getStatesWithSpecificTimezone(@RequestParam(value = "zone") String timezone) {
        return stateServiceClient.getStatesWithSpecificTimezone(timezone);
    }

}
