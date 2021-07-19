package edu.ait.capitalservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "US_State_Capitals")
@ApiModel(description = "Information relating to US state capitals")
public class Capital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The id is automatically generated on creation")
    private Integer id;
    @NotBlank(message = "The state capital name cannot be blank")
    @ApiModelProperty(notes = "The state capital name cannot be blank")
    private String name;
    @NotBlank(message = "The state capital motto cannot be blank")
    @ApiModelProperty(notes = "The state capital motto cannot be blank")
    private String motto;
    @NotBlank(message = "The state capital nickname cannot be blank")
    @ApiModelProperty(notes = "The state capital nickname cannot be blank")
    private String nickname;
    @Column(name = "incorporated", columnDefinition = "DATE")
    @Past(message = "The date the state capital was incorporated must be in the past")
    @ApiModelProperty(notes = "The date the state capital was incorporated must be in the past. The date format is YYYY-MM-DD")
    private java.time.LocalDate incorporated;
    @Positive(message = "The state population must be greater than zero")
    @ApiModelProperty(notes = "The state population must be greater than zero")
    private int population;
    @NotBlank(message = "The state capital demonym cannot be blank")
    @ApiModelProperty(notes = "The state capital demonym cannot be blank")
    private String demonym;

    public Capital() {
    }

    public Capital(Integer id, String name, String motto, String nickname, LocalDate incorporated, int population, String demonym) {
        this.id = id;
        this.name = name;
        this.motto = motto;
        this.nickname = nickname;
        this.incorporated = incorporated;
        this.population = population;
        this.demonym = demonym;
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

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getIncorporated() {
        return incorporated;
    }

    public void setIncorporated(LocalDate incorporated) {
        this.incorporated = incorporated;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    @Override
    public String toString() {
        return "Capital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", motto='" + motto + '\'' +
                ", nickname='" + nickname + '\'' +
                ", incorporated=" + incorporated +
                ", population=" + population +
                ", demonym='" + demonym + '\'' +
                '}';
    }
}
