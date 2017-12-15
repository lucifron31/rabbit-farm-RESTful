package lucifron.projects.springtest.controller;

import lucifron.projects.springtest.model.Rabbit;
import lucifron.projects.springtest.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    // -------------------Получить всех кроликов великанов---------------------------------------------

    @RequestMapping(value = "/rabbit/", method = RequestMethod.GET)
    public ResponseEntity<List<Rabbit>> listAllUsers() {
        try {
            return new ResponseEntity<>(rabbitService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    // -------------------Найти кролика великана по имени------------------------------------------

    @RequestMapping(value = "/rabbit/{rabbit}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("rabbit") String name) {
        try {
            return new ResponseEntity(rabbitService.findByName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new Exception("rabbit with name " + name
                    + " not found"), HttpStatus.NOT_FOUND);
        }

    }
    // -------------------Создать кролика великана-------------------------------------------

    @RequestMapping(value = "/rabbit/{name}&{color}", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(
            /*@RequestBody Rabbit rabbit
            ,*/ @PathVariable("name") String name
            , @PathVariable("color") String color
            , UriComponentsBuilder ucBuilder) {
        Rabbit rabbit;
        try {
            rabbit = new Rabbit(name, color);
            rabbitService.createRabbit(rabbit);
        } catch (Exception e) {
            return new ResponseEntity(new Exception("Unable to create. A User with name " +
                    /*rabbit.getName() +*/ " already exist."), HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rabbit_api/rabbit/{name}&{color}").buildAndExpand(rabbit.getName()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    /**
     * Создать кролика великана.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/rabbit/new/{name}&{color}", method = RequestMethod.GET)
    public Rabbit createRabbit(Model model, @PathVariable("name") String name, @PathVariable("color") String color) throws Exception {
        Rabbit rabbit = new Rabbit(name, color);
        rabbitService.createRabbit(rabbit);
        model.addAttribute("rabbit", rabbit);
        return rabbit;
    }
}
