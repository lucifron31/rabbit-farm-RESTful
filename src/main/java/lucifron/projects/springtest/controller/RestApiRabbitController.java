package lucifron.projects.springtest.controller;

import lucifron.projects.springtest.model.Rabbit;
import lucifron.projects.springtest.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


/**
 * Класс контролер SpringApp
 */
@RestController
@RequestMapping("/rabbit_api")
public class RestApiRabbitController {

    @Autowired
    RabbitService rabbitService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Rabbit---------------------------------------------

    @RequestMapping(value = "/rabbit/", method = RequestMethod.GET)
    public ResponseEntity<List<Rabbit>> listAllUsers() {
        try {
            return new ResponseEntity<>(rabbitService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    // -------------------Create a Rabbit-------------------------------------------

    @RequestMapping(value = "/rabbit/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Rabbit rabbit, UriComponentsBuilder ucBuilder) {

        try {
            rabbitService.createRabbit(rabbit);
        } catch (Exception e) {
            return new ResponseEntity(new Exception("Unable to create. A User with name " +
                    rabbit.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rabbit_api/rabbit/{id}").buildAndExpand(rabbit.getName()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
