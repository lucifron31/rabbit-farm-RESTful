package lucifron.projects.springtest.service;

import lombok.Getter;
import lombok.Setter;
import lucifron.projects.springtest.model.Rabbit;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Service("rabbitService")
public class RabbitServiceImpl implements RabbitService {

    @Getter
    @Setter
    private static ArrayList<Rabbit> rabbits;

    static {
        rabbits = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            rabbits.add(new Rabbit("кролик" + i, "", i, null));
        }

    }

    @Override
    public List<Rabbit> findAll() throws Exception {
        return rabbits;
    }

    @Override
    public Rabbit findByName(String name) throws Exception {
        if (name != null && !name.isEmpty()) {
            if (!rabbits.isEmpty()) {
                for (Rabbit rabbit : rabbits) {
                    if (name.equals(rabbit.getName())) {
                        return rabbit;
                    }
                }
            }
            throw new Exception("Кролик с именем \"" + name + "\" не найден.");
        } else throw new Exception("Имя кролика некорректное!");
    }

    @Override
    public Rabbit findByColor(String color) throws Exception {
        if (color != null && !color.isEmpty()) {
            if (!rabbits.isEmpty()) {
                for (Rabbit rabbit : rabbits) {
                    if (color.equals(rabbit.getColor())) {
                        return rabbit;
                    }
                }
            }
            throw new Exception("Кролик с цветом \"" + color + "\" не найден.");
        } else throw new Exception("Имя кролика некорректное!");
    }

    @Override
    public Rabbit findByAge(int age) throws Exception {
        if (!rabbits.isEmpty()) {
            for (Rabbit rabbit : rabbits) {
                if (age == rabbit.getAge()) {
                    return rabbit;
                }
            }
        }
        throw new Exception("Кролик с возрастом \"" + age + "\" не найден.");
    }

    @Override
    public Rabbit findByPhoto(URL url) throws Exception {
        if (!rabbits.isEmpty()) {
            for (Rabbit rabbit : rabbits) {
                if (url.toURI().equals(rabbit.getPhoto().toURI())) {
                    return rabbit;
                }
            }
        }
        throw new Exception("Кролик с возрастом \"" + url.toString() + "\" не найден.");
    }

    @Override
    public void createRabbit(Rabbit rabbit) throws Exception {
        if (rabbit != null) {
            rabbits.add(rabbit);
        }
    }

    @Override
    public void updateRabbit(Rabbit rabbit) throws Exception {
        final String rabbitName = rabbit.getName();
        if (rabbitName == null ) {
            throw new Exception("Ошибка при обновлении кролика!");
        }

        if (rabbit != null) {
            if (!rabbits.isEmpty()) {
                for (Rabbit rabbitEntry : rabbits) {
                    if (rabbitName.equals(rabbitEntry.getName())) {
                        rabbitEntry.setAge(rabbit.getAge());
                        rabbitEntry.setColor(rabbit.getColor());
                        rabbitEntry.setPhoto(rabbit.getPhoto());
                    }
                }
            }
        } else throw new Exception("Ошибка при обновлении кролика!");
    }

    @Override
    public void deleteRabbit(Rabbit rabbit) throws Exception {
        final String rabbitName = rabbit.getName();
        if (rabbit != null && rabbitName != null && !rabbitName.isEmpty()) {
            if (!rabbits.isEmpty()) {
                for (int i = 0; i < rabbits.size(); i++) {
                    final Rabbit rabbitEntry = rabbits.get(i);
                    if (rabbitName.equals(rabbitEntry.getName())) {
                        rabbits.remove(i);
                    }
                }
            }
        } else throw new Exception("Ошибка при удалении кролика!");
    }
}
