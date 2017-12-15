package lucifron.projects.springtest.service;

import lucifron.projects.springtest.model.Rabbit;

import java.net.URL;
import java.util.List;


/**
 * Интерфейс описывающий операции CRUD для кроликов великанов.
 */
public interface RabbitService {

    List<Rabbit> findAll() throws Exception;

    Rabbit findByName(String name) throws Exception;

    Rabbit findByColor(String color) throws Exception;

    Rabbit findByAge(int age) throws Exception;

    Rabbit findByPhoto(URL url) throws Exception;

    void createRabbit(Rabbit rabbit) throws Exception;

    void updateRabbit(Rabbit rabbit) throws Exception;

    void deleteRabbit(Rabbit rabbit) throws Exception;
}
