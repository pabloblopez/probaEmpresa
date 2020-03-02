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
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {
    private List<People> results;

    public Results() {
    }

    public List<People> getResults() {
        return results;
    }

    public void setResults(List<People> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Results{" + "results=" + results + '}';
    }
    
}
