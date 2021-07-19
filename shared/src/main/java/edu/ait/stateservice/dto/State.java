package edu.ait.stateservice.dto;

import edu.ait.capitalservice.dto.Capital;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "US_States")
@ApiModel(description = "Information relating to US states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The id is automatically generated on creation")
    private Integer id;
    @NotBlank(message = "The state name cannot be blank")
    @ApiModelProperty(notes = "The state name cannot be blank")
    private String name;
    @Size(min = 2, max = 2, message = "The state abbreviation must be 2 characters long")
    @ApiModelProperty(notes = "The state abbreviation must be 2 characters long. For example CT for Connecticut or IN for Indiana")
    private String abbreviation;
    @Column(name = "founded", columnDefinition = "DATE")
    @Past(message = "The date the state was founded must be in the past")
    @ApiModelProperty(notes = "The date the state was founded must be in the past. The date format is YYYY-MM-DD")
    private java.time.LocalDate founded;
    @NotBlank(message = "The state nickname cannot be blank")
    @ApiModelProperty(notes = "The state nickname cannot be blank")
    private String nickname;
    @Positive(message = "The state population must be greater than zero")
    @ApiModelProperty(notes = "The state population must be greater than zero")
    private int population;
    @NotBlank(message = "The state timezone cannot be blank")
    @ApiModelProperty(notes = "The state timezone cannot be blank. US timezones are Eastern, Central, Mountain, Pacific, Alaskan and Hawaii-Aleutian")
    private String timezone;

    @Transient
    private String stateCapital;

    public State() {
    }

    public State(Integer id, String name, String abbreviation, LocalDate founded, String nickname, int population, String timezone) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.founded = founded;
        this.nickname = nickname;
        this.population = population;
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getStateCapital() {
        return stateCapital;
    }

    public void setStateCapital(String stateCapital) {
        this.stateCapital = stateCapital;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", founded=" + founded +
                ", nickname='" + nickname + '\'' +
                ", population='" + population + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
