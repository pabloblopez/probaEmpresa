/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author paulo
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor not found")
public class PeopleNotFoundException extends RuntimeException {

    public PeopleNotFoundException(HttpStatus code, String reason) {
    }    
}
