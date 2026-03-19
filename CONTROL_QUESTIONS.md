# Відповіді на контрольні запитання

## 1. Які програми називаються багатопоточними?

**Багатопоточні програми** - це програми, які можуть одночасно виконувати кілька незалежних потоків (threads) в рамках одного процесу. Кожен потік має власний стек виконання, але спільно використовує пам'ять процесу, що дозволяє ефективно обмінюватися даними.

**Основні характеристики:**
- Паралельне виконання кількох послідовностей інструкцій
- Спільний доступ до пам'яті процесу
- Незалежні потоки управління
- Можливість синхронізації між потоками

**Переваги багатопоточності:**
- Підвищення продуктивності на багатоядерних процесорах
- Покращення відгуку інтерфейсу користувача
- Ефективне використання часу очікування I/O операцій
- Логічне розділення задач

---

## 2. Які засоби організації багатопоточних програм є в мові програмування Java та як їх використовувати?

### Основні засоби Java для багатопоточності:

### 2.1. Клас Thread
Базовий клас для створення потоків.

**Спосіб 1: Успадкування від Thread**
```java
class MyThread extends Thread {
    @Override
    public void run() {
        // Код, що виконується в потоці
        System.out.println("Потік запущено");
    }
}

// Використання:
MyThread thread = new MyThread();
thread.start();
```

**Спосіб 2: Реалізація інтерфейсу Runnable**
```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        // Код потоку
    }
}

// Використання:
Thread thread = new Thread(new MyRunnable());
thread.start();
```

### 2.2. Основні методи класу Thread
- `start()` - запускає потік
- `run()` - метод, що містить код потоку
- `join()` - очікування завершення потоку
- `sleep(long millis)` - призупинення потоку на час
- `interrupt()` - переривання потоку
- `isAlive()` - перевірка чи активний потік

### 2.3. Синхронізація
**synchronized keyword:**
```java
public synchronized void method() {
    // Синхронізований код
}

synchronized(object) {
    // Синхронізований блок
}
```

### 2.4. Volatile змінні
```java
private volatile boolean running = true;
```
Гарантує видимість змін між потоками.

### 2.5. Класи синхронізації (java.util.concurrent)
- `ExecutorService` - керування пулом потоків
- `Semaphore` - семафори для контролю доступу
- `CountDownLatch` - синхронізація початку/завершення
- `CyclicBarrier` - точки синхронізації
- `ReentrantLock` - блокування з розширеними можливостями

**Приклад з ExecutorService:**
```java
ExecutorService executor = Executors.newFixedThreadPool(5);
executor.submit(() -> {
    // Задача для виконання
});
executor.shutdown();
```

### 2.6. Atomic класи
```java
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();
```

---

## 3. Які засоби організації багатопоточних програм є в мові програмування C# та як їх використовувати?

### Основні засоби C# для багатопоточності:

### 3.1. Клас Thread (System.Threading)
```csharp
using System.Threading;

class Program {
    static void ThreadMethod() {
        Console.WriteLine("Потік виконується");
    }
    
    static void Main() {
        Thread thread = new Thread(ThreadMethod);
        thread.Start();
        thread.Join();
    }
}
```

### 3.2. ThreadPool
```csharp
ThreadPool.QueueUserWorkItem(state => {
    Console.WriteLine("Робота в пулі потоків");
});
```

### 3.3. Task (Task Parallel Library - TPL)
Сучасний підхід до багатопоточності в C#:
```csharp
using System.Threading.Tasks;

Task task = Task.Run(() => {
    // Код задачі
});
await task;
```

### 3.4. async/await
```csharp
async Task<string> GetDataAsync() {
    await Task.Delay(1000);
    return "Дані";
}

// Використання:
string result = await GetDataAsync();
```

### 3.5. Parallel класи
```csharp
// Паралельний цикл
Parallel.For(0, 100, i => {
    Console.WriteLine($"Ітерація {i}");
});

// Паралельний foreach
Parallel.ForEach(collection, item => {
    // Обробка елемента
});
```

### 3.6. Синхронізація в C#
**lock statement:**
```csharp
private object lockObject = new object();

lock(lockObject) {
    // Критична секція
}
```

**Monitor:**
```csharp
Monitor.Enter(lockObject);
try {
    // Критична секція
} finally {
    Monitor.Exit(lockObject);
}
```

**Semaphore:**
```csharp
Semaphore semaphore = new Semaphore(3, 3);
semaphore.WaitOne();
try {
    // Робота
} finally {
    semaphore.Release();
}
```

### 3.7. Concurrent колекції
```csharp
using System.Collections.Concurrent;

ConcurrentQueue<int> queue = new ConcurrentQueue<int>();
ConcurrentDictionary<string, int> dict = new ConcurrentDictionary<string, int>();
```

---

## 4. Які засоби організації багатопоточних програм є в мові програмування Ada та як їх використовувати?

### Основні засоби Ada для багатопоточності:

### 4.1. Task (Задачі)
Ada має вбудовану підтримку багатопоточності через конструкцію **task**.

**Простий task:**
```ada
task My_Task is
end My_Task;

task body My_Task is
begin
    -- Код задачі
    Put_Line("Задача виконується");
end My_Task;
```

### 4.2. Task Type (Тип задачі)
```ada
task type Worker is
    entry Start(N: Integer);
    entry Stop;
end Worker;

task body Worker is
    Value: Integer;
begin
    accept Start(N: Integer) do
        Value := N;
    end Start;
    
    -- Робота
    
    accept Stop;
end Worker;

-- Створення екземплярів:
W1: Worker;
W2: Worker;
```

### 4.3. Entry Points (Точки входу)
Механізм для синхронізації та обміну даними між задачами:
```ada
task Server is
    entry Request(Data: in String);
end Server;

task body Server is
begin
    loop
        accept Request(Data: in String) do
            -- Обробка запиту
            Put_Line(Data);
        end Request;
    end loop;
end Server;

-- Виклик:
Server.Request("Hello");
```

### 4.4. Select Statement
Для вибіркового очікування подій:
```ada
task body Server is
begin
    loop
        select
            accept Request1 do
                -- Обробка запиту 1
            end Request1;
        or
            accept Request2 do
                -- Обробка запиту 2
            end Request2;
        or
            delay 10.0;
            exit; -- Тайм-аут
        end select;
    end loop;
end Server;
```

### 4.5. Protected Objects (Захищені об'єкти)
Для безпечного доступу до спільних даних:
```ada
protected type Shared_Counter is
    procedure Increment;
    function Get_Value return Integer;
private
    Value: Integer := 0;
end Shared_Counter;

protected body Shared_Counter is
    procedure Increment is
    begin
        Value := Value + 1;
    end Increment;
    
    function Get_Value return Integer is
    begin
        return Value;
    end Get_Value;
end Shared_Counter;

-- Використання:
Counter: Shared_Counter;
Counter.Increment;
```

### 4.6. Rendezvous (Рандеву)
Синхронізація задач через accept-entry:
```ada
task T1;
task T2 is
    entry Meet;
end T2;

task body T1 is
begin
    -- Виклик entry - чекає на accept
    T2.Meet;
end T1;

task body T2 is
begin
    -- Прийняття виклику
    accept Meet do
        -- Синхронізована робота
    end Meet;
end T2;
```

### 4.7. Terminate Alternative
```ada
task body Worker is
begin
    loop
        select
            accept Do_Work do
                -- Робота
            end Do_Work;
        or
            terminate; -- Завершення при відсутності викликів
        end select;
    end loop;
end Worker;
```

### 4.8. Приклад багатопоточної програми на Ada
```ada
with Ada.Text_IO; use Ada.Text_IO;

procedure Multitasking is
    task type Sum_Task(ID: Integer; Step: Integer) is
        entry Start;
        entry Stop;
    end Sum_Task;
    
    task body Sum_Task is
        Sum: Integer := 0;
        Count: Integer := 0;
        Value: Integer := 0;
        Running: Boolean := True;
    begin
        accept Start;
        
        while Running loop
            select
                accept Stop do
                    Running := False;
                end Stop;
            or
                delay 0.001;
                Sum := Sum + Value;
                Count := Count + 1;
                Value := Value + Step;
            end select;
        end loop;
        
        Put_Line("Task" & Integer'Image(ID) & 
                 ": Sum =" & Integer'Image(Sum) & 
                 ", Count =" & Integer'Image(Count));
    end Sum_Task;
    
    T1: Sum_Task(1, 5);
    T2: Sum_Task(2, 10);
begin
    T1.Start;
    T2.Start;
    delay 1.0;
    T1.Stop;
    T2.Stop;
end Multitasking;
```

---

## Порівняння підходів

| Особливість | Java | C# | Ada |
|------------|------|-----|-----|
| Основний механізм | Thread/Runnable | Thread/Task | Task (вбудований) |
| Синхронізація | synchronized, Lock | lock, Monitor | Protected Objects |
| Комунікація | Shared memory | Shared memory | Entry/Accept, Protected |
| Рівень абстракції | Середній | Високий (async/await) | Високий (rendezvous) |
| Безпека | Середня | Висока | Дуже висока |

---

## Висновок

Кожна мова програмування має свої унікальні засоби для роботи з багатопоточністю:

- **Java** - класичний підхід з Thread та сучасні concurrent utilities
- **C#** - еволюція від Thread до Task та async/await
- **Ada** - вбудована підтримка з високим рівнем безпеки та синхронізації

Вибір конкретного підходу залежить від вимог проекту, екосистеми та необхідного рівня контролю над потоками.

