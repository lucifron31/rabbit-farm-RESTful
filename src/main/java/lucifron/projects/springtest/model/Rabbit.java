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

  /*  public Rabbit(String name, String color, int age, URL photo) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.photo = photo;
    }*/
}
