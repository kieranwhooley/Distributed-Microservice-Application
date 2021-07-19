package edu.ait.stateservice.repositories;

import edu.ait.stateservice.dto.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository <State, Integer> {

    List<State> findByPopulationGreaterThan(int population);
    List<State> findByNameContainingIgnoreCase(String name);
    List<State> findByTimezoneIgnoreCase(String timezone);
    List<State> findByFoundedBefore (java.time.LocalDate foundedDate);
    List<State> findByFoundedAfter (java.time.LocalDate foundedDate);

}
