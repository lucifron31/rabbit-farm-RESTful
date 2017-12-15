package lucifron.projects.springtest.model;

import java.net.URL;


/**
 * Класс-модель, описывающая кролика великана.
 */
@lombok.Data
@lombok.AllArgsConstructor
public class Rabbit {

    /**
     * Имя
     */
    private String name;

    /**
     * Цвет
     */
    private String color;

    /**
     * Возраст
     */
    private int age;

    /**
     * Фотография
     */
    private URL photo;
}
