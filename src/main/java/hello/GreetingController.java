package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {
    private static final String URIPART = "/swapi-proxy/person-info";
    private static final String URIBASE = "https://swapi.co/api/";
    private RestTemplate restTemplate;
    private HttpEntity<String> entity;
    private Results quote;
    private boolean found;
    
    @RequestMapping(URIPART)
    public ResultPeople greeting(@RequestParam(value="name", defaultValue="World")
            String name) {
        String urlTmp;
        Iterator<People> it;
        People actor;
        ResultPeople resultActor;
        Planet planet;
        
        found = false;
        restTemplate = new RestTemplate();
        entity = this.getEntity();
        resultActor = null;
        actor = null;
        try {
            urlTmp = URIBASE + "people?name=" + name + "&format=json";
            quote = restTemplate.exchange(urlTmp, 
                    HttpMethod.GET,entity, Results.class).getBody();
            if (quote != null) {
                it = quote.getResults().iterator();
                while (it.hasNext() && found == false) {
                    actor = it.next();
                    if(name.equals(actor.getName()))  {
                        found = true;
                    }
                }
            }
            if (found && actor != null) {
                if (null != actor.homeworld) {
                    urlTmp = actor.homeworld + "?format=json";
                    //System.out.println(urlTmp);
                    planet = getPlanet(urlTmp);
                    actor.setPlanet_name(planet.getName());
                    //System.out.println("Atopado" + actor.getFilms() + ", " + actor.getVehicles() + ", " + actor.getStarships());
                    resultActor = new ResultPeople(actor, getFilms(actor.getFilms()), getVehicle(actor.getVehicles(), actor.getStarships()));
                    
                }
            }
            else {
                throw new PeopleNotFoundException(
                    HttpStatus.NOT_FOUND, "entity not found");                
            }
        }
        catch (Exception ex) {
            //System.out.println(ex.toString());
            throw new PeopleNotFoundException(
                HttpStatus.NOT_FOUND, "entity not found");
        }
        // org.springframework.web.client.HttpClientErrorException$NotFound: 404 NOT FOUND
        return resultActor;
    }
    
    private Planet getPlanet(String url) {
        Planet planet = restTemplate.exchange(url, 
                    HttpMethod.GET,entity, Planet.class).getBody();
        return planet;
    }
    
    private List<Film> getFilms(List<String> listFilms) {
        List<Film> list = new ArrayList<>();
        Iterator<String> it;
        String url;
        Film film;
        
        it = listFilms.iterator();
        while (it.hasNext()) {
            url = it.next();
            url = url + "?format=json";
            film = restTemplate.exchange(url, 
                    HttpMethod.GET,entity, Film.class).getBody();
            if (film != null) {
                list.add(film);
            }
        }
        return list;
    }
    
    private Vehicle getVehicle(List<String> urlVehicle, List<String> urlStarship) {
        Vehicle vehicleTmp, vehicle;
        Iterator<String> it;
        String url;
        int cont;
        
        vehicle = new Vehicle();
        vehicle.setName("noName");
        vehicle.setMax_atmosphering_speed("0");
        
        for (it = urlVehicle.iterator(), cont = 0; cont < 2; cont++) {
            while (it.hasNext()) {
                url = it.next();
                url = url + "?format=json";
                vehicleTmp = restTemplate.exchange(url, 
                        HttpMethod.GET,entity, Vehicle.class).getBody();
                if (vehicleTmp != null) {
                    if (Float.parseFloat(vehicleTmp.getMax_atmosphering_speed().trim()) > 
                            Float.parseFloat(vehicle.getMax_atmosphering_speed().trim())) {
                        vehicle.setName(vehicleTmp.getName());
                        vehicle.setMax_atmosphering_speed(vehicleTmp.getMax_atmosphering_speed());
                    }
                }      
            }
            it = urlStarship.iterator();
        }
        return vehicle;
    }
        
    private HttpEntity<String> getEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<>("parameters", headers); 
        return entity;
    }
}
