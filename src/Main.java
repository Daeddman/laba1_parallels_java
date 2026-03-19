import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Клас потоку для обчислення суми числової послідовності
 */
class SumThread extends Thread {
    private final int threadNumber;
    final int step;
    final int workTimeMs;  // Зроблено package-private для доступу в Main
    private long sum = 0;
    private int count = 0;

    public SumThread(int threadNumber, int step, int workTimeMs) {
        this.threadNumber = threadNumber;
        this.step = step;
        this.workTimeMs = workTimeMs;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        int currentValue = 0;

        // Працюємо поки не минув заданий час
        while (System.currentTimeMillis() - startTime < workTimeMs) {
            sum += currentValue;
            count++;
            currentValue += step;

            // Невелика затримка для демонстрації роботи
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }

        // Виведення результатів роботи потоку
        System.out.printf("Потік №%d завершено: Сума = %d, Кількість елементів = %d%n",
                         threadNumber, sum, count);
    }
}

public class Main {
    private static final int MAX_THREADS = 32;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Багатопоточна програма обчислення сум послідовностей ===");

        // Введення кількості потоків з валідацією
        int threadCount;
        while (true) {
            System.out.print("Введіть кількість потоків (1-" + MAX_THREADS + "): ");
            try {
                threadCount = scanner.nextInt();
                if (threadCount > 0 && threadCount <= MAX_THREADS) {
                    break;
                } else {
                    System.out.println("❌ Помилка: кількість потоків має бути від 1 до " + MAX_THREADS + "!");
                }
            } catch (Exception e) {
                System.out.println("❌ Помилка: введіть ціле число!");
                scanner.nextLine(); // Очистити буфер
            }
        }

        System.out.println("\n--- Параметри для кожного потоку ---");

        // Створення робочих потоків
        List<SumThread> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            System.out.println("\nПотік №" + (i + 1) + ":");

            // Введення кроку з валідацією
            int step;
            while (true) {
                System.out.print("  Введіть крок послідовності (>0): ");
                try {
                    step = scanner.nextInt();
                    if (step > 0) {
                        break;
                    } else {
                        System.out.println("  ❌ Помилка: крок має бути більше 0!");
                    }
                } catch (Exception e) {
                    System.out.println("  ❌ Помилка: введіть ціле число!");
                    scanner.nextLine(); // Очистити буфер
                }
            }

            // Введення часу з валідацією
            int workTime;
            while (true) {
                System.out.print("  Введіть час роботи в мс (>0): ");
                try {
                    workTime = scanner.nextInt();
                    if (workTime > 0) {
                        break;
                    } else {
                        System.out.println("  ❌ Помилка: час роботи має бути більше 0!");
                    }
                } catch (Exception e) {
                    System.out.println("  ❌ Помилка: введіть ціле число!");
                    scanner.nextLine(); // Очистити буфер
                }
            }

            SumThread thread = new SumThread(i + 1, step, workTime);
            threads.add(thread);
        }

        System.out.println("\n--- Запуск програми ---");

        // Виведення конфігурації
        for (int i = 0; i < threadCount; i++) {
            System.out.println("Потік №" + (i + 1) + ": крок=" + threads.get(i).step +
                             ", час=" + threads.get(i).workTimeMs + " мс");
        }
        System.out.println();

        // Одночасний запуск всіх потоків
        System.out.println("Запуск всіх потоків одночасно...\n");
        for (SumThread thread : threads) {
            thread.start();
        }

        // Очікування завершення всіх потоків
        try {
            for (SumThread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Помилка при очікуванні завершення потоків");
        }

        System.out.println("\n--- Програма завершена ---");
        scanner.close();
    }
}
