package edu.ait.stateservice.feignclients;

import edu.ait.capitalservice.dto.Capital;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("zuul-api-gateway")
public interface CapitalServiceClient {

    @GetMapping("/capital-service/capitals/{id}")
    Capital getCapitalById (@PathVariable int id);

}
