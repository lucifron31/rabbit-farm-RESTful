package lucifron.projects.springtest.service;

import lucifron.projects.springtest.model.Rabbit;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;


public class RabbitServiceImplTest {

    private RabbitServiceImpl service = new RabbitServiceImpl();

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void findByName() throws Exception {

    }

    @Test
    public void findByColor() throws Exception {

    }

    @Test
    public void findByAge() throws Exception {
    }

    @Test
    public void findByPhoto() throws Exception {
    }

    @Test
    public void createRabbit() throws Exception {
        service.getRabbits().clear();
        String rabbitName = "rabbit_1";
        String rabbitColor = "green";
        int rabbitAge = 1;
        URL rabbitPhoto = new URL("http://google.com");
        Rabbit rabbit = new Rabbit(rabbitName, rabbitColor, rabbitAge, rabbitPhoto);
        rabbit.setName(rabbitName);
        rabbit.setColor(rabbitColor);
        rabbit.setAge(rabbitAge);
        rabbit.setPhoto(rabbitPhoto);
        int j = rabbit.hashCode();
        service.createRabbit(rabbit);

        ArrayList<Rabbit> rabbits = service.getRabbits();
        boolean isExist = false;
        for (Rabbit rabbitEntry : rabbits) {
            int i = rabbitEntry.hashCode();
            if (i == j) {
                String format = MessageFormat.format(
                        "Кролик c менем {0} возрастом {1} лет, цветом {2} и фото смотрите {3} аккуратно помещен в клетку и ждет...."
                        , rabbitEntry.getName(), rabbitEntry.getAge(), rabbitEntry.getColor(), rabbitEntry.getPhoto());
                System.out.println(format);
                isExist = true;

            }
        }
        Assert.assertTrue(isExist);
    }

    @Test
    public void updateRabbit() throws Exception {
        RabbitServiceImpl.getRabbits().clear();
        String rabbitName = "rabbit_1";
        String rabbitColor = "green";
        int rabbitAge = 1;
        URL rabbitPhoto = new URL("http://google.com");
        Rabbit rabbitTom = new Rabbit(rabbitName, rabbitColor, rabbitAge, rabbitPhoto);
        rabbitTom.setName(rabbitName);
        rabbitTom.setColor(rabbitColor);
        rabbitTom.setAge(rabbitAge);
        rabbitTom.setPhoto(rabbitPhoto);

        ArrayList<Rabbit> rabbits = RabbitServiceImpl.getRabbits();
        rabbits.add(rabbitTom);

        Rabbit rabbitTomNew = new Rabbit(rabbitName, rabbitColor, rabbitAge, rabbitPhoto);
        rabbitTomNew.setName(rabbitName);
        rabbitTomNew.setColor("pink");
        rabbitTomNew.setAge(3);
        rabbitTomNew.setPhoto(rabbitPhoto);
        service.updateRabbit(rabbitTomNew);
        System.out.println(RabbitServiceImpl.getRabbits());
        Assert.assertEquals(rabbitTomNew, rabbitTom);
    }

    @Test
    public void deleteRabbit() throws Exception {

    }
}