import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

class SumWorker extends Thread {
    private final int id;
    private final int step;
    private final int workTimeMs;
    private final AtomicBoolean stopRequested = new AtomicBoolean(false);
    private BigInteger sum = BigInteger.ZERO;
    private long count = 0;

    public SumWorker(int id, int step, int workTimeMs) {
        if (step <= 0) {
            throw new IllegalArgumentException("step must be > 0");
        }
        if (workTimeMs <= 0) {
            throw new IllegalArgumentException("workTimeMs must be > 0");
        }
        this.id = id;
        this.step = step;
        this.workTimeMs = workTimeMs;
        setName("SumWorker-" + id);
    }

    public int getWorkTimeMs() {
        return workTimeMs;
    }

    public void requestStop() {
        stopRequested.set(true);
    }

    @Override
    public void run() {
        BigInteger currentValue = BigInteger.ZERO;
        BigInteger bigStep = BigInteger.valueOf(step);

        while (!stopRequested.get()) {
            sum = sum.add(currentValue);
            currentValue = currentValue.add(bigStep);
            count++;
        }

        System.out.printf(
                "[Потік %d] завершив роботу. Крок: %d | Доданків: %d | Сума: %s%n",
                id, step, count, sum.toString()
        );
    }
}

class ControllerThread extends Thread {
    private final List<SumWorker> workers;

    public ControllerThread(List<SumWorker> workers) {
        this.workers = workers;
        setName("ControllerThread");
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        boolean[] stopSent = new boolean[workers.size()];
        int active = workers.size();

        while (active > 0) {
            long elapsedMs = (System.nanoTime() - start) / 1_000_000;

            for (int i = 0; i < workers.size(); i++) {
                if (stopSent[i]) {
                    continue;
                }

                SumWorker worker = workers.get(i);
                if (elapsedMs >= worker.getWorkTimeMs()) {
                    worker.requestStop();
                    stopSent[i] = true;
                    active--;
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {
                interrupt();
                return;
            }
        }
    }
}

public class Main {
    private static final int MIN_THREADS = 1;
    private static final int MAX_THREADS = 32;
    private static final int MAX_STEP = 1_000_000;
    private static final int MAX_WORK_TIME = 60_000;

    private static int readInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextInt()) {
                System.out.println("Помилка. Введіть ціле число.");
                scanner.next();
                continue;
            }
            int value = scanner.nextInt();
            if (value >= min && value <= max) {
                return value;
            }
            System.out.printf("Помилка. Введіть число в межах від %d до %d.%n", min, max);
        }
    }

static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Лабораторна робота №1");
        System.out.println("Тема: Засоби створення багатопоточних програм");
        System.out.println();

        int threadCount = readInt(scanner,
                "Кількість потоків (1-32): ",
                MIN_THREADS,
                MAX_THREADS);
        System.out.println();

        List<SumWorker> workers = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            System.out.println("Налаштування потоку " + (i + 1) + ":");
            int step = readInt(scanner,
                    "  Крок (1-1000000): ",
                    1,
                    MAX_STEP);
            int workTime = readInt(scanner,
                    "  Час роботи в мс (1-60000): ",
                    1,
                    MAX_WORK_TIME);
            workers.add(new SumWorker(i + 1, step, workTime));
            System.out.println();
        }

        for (SumWorker worker : workers) {
            worker.start();
        }

        ControllerThread controller = new ControllerThread(workers);
        controller.start();

        for (SumWorker worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Перервано під час очікування робочого потоку.");
                return;
            }
        }

        try {
            controller.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Перервано під час очікування керуючого потоку.");
            return;
        }

        System.out.println();
        System.out.println("Усі потоки завершили роботу.");
    }
}
