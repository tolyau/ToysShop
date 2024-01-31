import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.StringTokenizer;

class Toy {
    private int id;
    private String name;
    private int weight; // Вес игрушки (частота выпадения)

    public Toy(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}

public class ToyStore {
    private PriorityQueue<Toy> toyQueue;

    public ToyStore() {
        toyQueue = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.getWeight(), t1.getWeight()));
    }

    public void put(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input);
        if (tokenizer.countTokens() == 3) {
            try {
                int id = Integer.parseInt(tokenizer.nextToken());
                String name = tokenizer.nextToken();
                int weight = Integer.parseInt(tokenizer.nextToken());
                Toy toy = new Toy(id, name, weight);
                toyQueue.add(toy);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка при чтении числового значения. Пропущена запись: " + input);
            }
        } else {
            System.out.println("Некорректный формат строки: " + input);
        }
    }

    public int get() {
        Random rand = new Random();
        int randNum = rand.nextInt(100) + 1; // случайное число от 1 до 100

        if (randNum <= 20) {
            return 1;
        } else if (randNum <= 40) {
            return 2;
        } else {
            return 3;
        }
    }

    public void playAndWriteResults(int times) {
        try (FileWriter writer = new FileWriter("results.txt")) {
            for (int i = 0; i < times; i++) {
                int result = get();
                if (result != -1) {
                    writer.write(result + "\n");
                } else {
                    System.out.println("Не удалось выбрать игрушку.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        // Добавление игрушек
        toyStore.put("1 Конструктор 20");
        toyStore.put("2 Робот 30");
        toyStore.put("3 Кукла 50");

        // Розыгрыш игрушек и запись результатов в файл
        toyStore.playAndWriteResults(10);
    }
}
