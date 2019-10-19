/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author paulo
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class People {
    @JsonProperty("name")
    private String name;
    @JsonProperty("birth_year")
    private String birth_year;
    @JsonProperty("gender")    
    private String gender;
    @JsonProperty("planet_name")
    private String planet_name;
    String homeworld;
    @JsonProperty("fasted_vehicle_driven")
    private String fastest_vehicle_driven;
    @JsonProperty("films")
    private List<String> films;
    private List<String> vehicles;
    private List<String> starships;

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }
    
    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlanet_name() {
        return planet_name;
    }

    public void setPlanet_name(String planet_name) {
        this.planet_name = planet_name;
    }

    public String getFastest_vehicle_driven() {
        return fastest_vehicle_driven;
    }

    public void setFastest_vehicle_driven(String fastest_vehicle_driven) {
        this.fastest_vehicle_driven = fastest_vehicle_driven;
    }  
}
