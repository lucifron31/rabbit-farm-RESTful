package lucifron.projects.springtest.service;

import lombok.NonNull;
import lucifron.projects.springtest.model.Rabbit;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;


public class RabbitServiceImplTest {

    private RabbitServiceImpl service = new RabbitServiceImpl();

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void findByName() throws Exception {
        String rabbitName = "Tom";
        Rabbit rabbit = new Rabbit(
                rabbitName,
                "black",
                2,
                new URL("http://google.com")
        );
        rabbitsClean();
        ArrayList<Rabbit> rabbits = RabbitServiceImpl.getRabbits();
        rabbits.add(rabbit);
        Assert.assertTrue(isExistRabbit(rabbit));
        Assert.assertEquals(rabbit, service.findByName(rabbitName));
    }

    @Test
    public void findByColor() throws Exception {
        String rabbitColor = "black";
        Rabbit rabbit = new Rabbit(
                "Tom",
                rabbitColor,
                2,
                new URL("http://google.com")
        );
        rabbitsClean();
        ArrayList<Rabbit> rabbits = RabbitServiceImpl.getRabbits();
        rabbits.add(rabbit);
        Assert.assertTrue(isExistRabbit(rabbit));
        Assert.assertEquals(rabbit, service.findByColor(rabbitColor));
    }

    @Test
    public void findByAge() throws Exception {
        int rabbitAge = 2;
        Rabbit rabbit = new Rabbit(
                "Tom",
                "black",
                rabbitAge,
                new URL("http://google.com")
        );
        rabbitsClean();
        ArrayList<Rabbit> rabbits = RabbitServiceImpl.getRabbits();
        rabbits.add(rabbit);
        Assert.assertTrue(isExistRabbit(rabbit));
        Assert.assertEquals(rabbit, service.findByAge(rabbitAge));
    }

    @Test
    public void findByPhoto() throws Exception {

    }

    @Test
    public void createRabbit() throws Exception {
        Rabbit rabbit = new Rabbit(
                "Tom",
                "black",
                2,
                new URL("http://google.com")
        );
        rabbitsClean();
        service.createRabbit(rabbit);
        Assert.assertTrue(isExistRabbit(rabbit));
    }

    @Test
    public void updateRabbit() throws Exception {
        rabbitsClean();
        Rabbit rabbit = new Rabbit(
                "Tom",
                "black",
                2,
                new URL("http://google.com")
        );
        int oldRabbitHashCode = rabbit.hashCode();
        // Add rabbit to box
        ArrayList<Rabbit> rabbits = RabbitServiceImpl.getRabbits();
        rabbits.add(rabbit);
        Assert.assertTrue(isExistRabbit(rabbit));
        Rabbit betterRabbit = new Rabbit(
                "Tom",
                "black",
                2,
                new URL("http://google.com")
        );
        int betterRabbitHashCode = betterRabbit.hashCode();
        // Rabbits is equals
        Assert.assertEquals(oldRabbitHashCode, betterRabbitHashCode);
        // Change color
        betterRabbit.setColor("pink");
        service.updateRabbit(betterRabbit);
        Assert.assertNotEquals(oldRabbitHashCode, betterRabbit.hashCode());
    }

    @Test
    public void deleteRabbit() throws Exception {
        Rabbit rabbit = new Rabbit(
                "Tom",
                "black",
                2,
                new URL("http://google.com")
        );
        rabbitsClean();
        ArrayList<Rabbit> rabbits = RabbitServiceImpl.getRabbits();
        rabbits.add(rabbit);
        Assert.assertTrue(isExistRabbit(rabbit));
        service.deleteRabbit(rabbit);
        Assert.assertFalse(isExistRabbit(rabbit));
    }

    private boolean isExistRabbit(@NonNull Rabbit rabbit) {
        int rabbitHashCode = rabbit.hashCode();
        ArrayList<Rabbit> rabbits = RabbitServiceImpl.getRabbits();
        for (Rabbit rabbitEntry : rabbits) {
            if (rabbitHashCode == rabbitEntry.hashCode())
                return true;
        }

        return false;
    }

    private void rabbitsClean() {
        ArrayList<Rabbit> rabbits = RabbitServiceImpl.getRabbits();
        if (rabbits != null)
            rabbits.clear();
    }
}