package edu.ait.capitalservice.repositories;

import edu.ait.capitalservice.dto.Capital;
import edu.ait.stateservice.dto.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CapitalRepository extends JpaRepository <Capital, Integer> {

    List<Capital> findByPopulationGreaterThan(int population);
    List<Capital> findByIncorporatedBefore (java.time.LocalDate incorporatedDate);
    List<Capital> findByIncorporatedAfter (java.time.LocalDate incorporatedDate);
    List<Capital> findByDemonymStartingWithIgnoreCase (String character);

}
