# Git інструкції для публікації проекту

## Публікація на GitHub

### Крок 1: Ініціалізація Git репозиторію

```bash
cd "/Users/daeddman/IntellIJ IDEA Projects/untitled"
git init
```

### Крок 2: Додавання файлів до репозиторію

```bash
git add .
```

### Крок 3: Перший коміт

```bash
git commit -m "Initial commit: Multithreading Lab Work (Java)

- Implemented SumThread class for parallel sum calculation
- Implemented ControllerThread for thread management
- Added comprehensive documentation
- Added test scripts and examples
- Ready for lab work submission"
```

### Крок 4: Створення репозиторію на GitHub

1. Відкрийте https://github.com
2. Натисніть "New repository"
3. Назва репозиторію: `multithreading-lab-java`
4. Опис: `Lab work: Multithreading in Java - Sum calculation with multiple threads`
5. Оберіть Public або Private
6. **НЕ** додавайте README, .gitignore, license (вони вже є)
7. Натисніть "Create repository"

### Крок 5: Підключення віддаленого репозиторію

```bash
# Замініть YOUR_USERNAME на ваш GitHub username
git remote add origin https://github.com/YOUR_USERNAME/multithreading-lab-java.git
```

### Крок 6: Перейменування гілки на main (якщо потрібно)

```bash
git branch -M main
```

### Крок 7: Завантаження коду на GitHub

```bash
git push -u origin main
```

---

## Альтернатива: SSH замість HTTPS

Якщо у вас налаштований SSH ключ:

```bash
git remote add origin git@github.com:YOUR_USERNAME/multithreading-lab-java.git
git push -u origin main
```

---

## Структура опублікованого репозиторію

```
multithreading-lab-java/
│
├── src/
│   └── Main.java                    # Головна програма
│
├── README.md                         # Основна документація
├── QUICKSTART.md                     # Швидкий старт
├── LAB_INSTRUCTIONS.md               # Інструкції до лабораторної
├── CONTROL_QUESTIONS.md              # Відповіді на питання
├── EXAMPLES.md                       # Приклади виконання
├── SUMMARY.md                        # Підсумок проекту
├── GIT_GUIDE.md                      # Ця інструкція
│
├── run_tests.sh                      # Скрипт автотестування
└── .gitignore                        # Git ignore rules
```

---

## Подальша робота з репозиторієм

### Додавання нових файлів

```bash
git add .
git commit -m "Add: description of changes"
git push
```

### Перегляд статусу

```bash
git status
```

### Перегляд історії

```bash
git log --oneline
```

### Створення нової гілки для експериментів

```bash
git checkout -b experiments
# ... внесіть зміни ...
git add .
git commit -m "Experimental feature"
git push -u origin experiments
```

---

## Клонування репозиторію (для інших користувачів)

### HTTPS:
```bash
git clone https://github.com/YOUR_USERNAME/multithreading-lab-java.git
cd multithreading-lab-java
```

### SSH:
```bash
git clone git@github.com:YOUR_USERNAME/multithreading-lab-java.git
cd multithreading-lab-java
```

---

## Інструкції для запуску (для тих, хто клонує)

Після клонування репозиторію:

```bash
# Перейти в директорію з кодом
cd multithreading-lab-java/src

# Компіляція
javac Main.java

# Запуск
java Main
```

Або використати автоматичні тести:

```bash
cd multithreading-lab-java
chmod +x run_tests.sh
./run_tests.sh
```

---

## Додавання README badges (опціонально)

Додайте на початок README.md:

```markdown
# Багатопоточна програма обчислення сум послідовностей (Java)

![Java](https://img.shields.io/badge/Java-21-orange)
![Status](https://img.shields.io/badge/Status-Ready-green)
![Lab Work](https://img.shields.io/badge/Type-Lab%20Work-blue)

...
```

---

## Посилання для звіту

Після публікації на GitHub, використовуйте такі посилання у звіті:

### Основне посилання на репозиторій:
```
https://github.com/YOUR_USERNAME/multithreading-lab-java
```

### Пряме посилання на код:
```
https://github.com/YOUR_USERNAME/multithreading-lab-java/blob/main/src/Main.java
```

### Інструкції для клонування:
```bash
git clone https://github.com/YOUR_USERNAME/multithreading-lab-java.git
```

---

## GitHub Pages (опціонально)

Для створення веб-сторінки з документацією:

1. Перейдіть в Settings репозиторію
2. Оберіть "Pages" в меню зліва
3. Source: "Deploy from a branch"
4. Branch: main, folder: / (root)
5. Save

Ваша документація буде доступна за адресою:
```
https://YOUR_USERNAME.github.io/multithreading-lab-java/
```

---

## Корисні команди Git

### Скасування змін (до коміту)
```bash
git restore Main.java
```

### Скасування останнього коміту (збереження змін)
```bash
git reset --soft HEAD~1
```

### Перегляд різниці
```bash
git diff
```

### Оновлення з віддаленого репозиторію
```bash
git pull
```

---

## .gitignore пояснення

Файл `.gitignore` вже налаштований для проекту:

- `*.class` - скомпільовані файли
- `.idea/` - налаштування IntelliJ IDEA
- `*.iml` - файли проекту IntelliJ
- `out/` - директорія виводу
- `.DS_Store` - системні файли macOS

---

## Приклад опису репозиторію на GitHub

**Description:**
```
Лабораторна робота з багатопоточного програмування на Java. 
Реалізація паралельного обчислення сум числових послідовностей з 
використанням Thread та керуючого потоку для синхронізації.
```

**Topics (tags):**
- java
- multithreading
- concurrency
- parallel-programming
- thread
- lab-work
- educational

---

## Readme для розділу "About" на GitHub

```
🧵 Multithreading Lab Work in Java

Parallel calculation of numeric sequence sums using multiple threads.
Features controller thread for synchronized shutdown and comprehensive documentation.

📚 Lab work on concurrent programming
🎯 Educational project
💻 Pure Java implementation
```

---

## Чеклист перед публікацією

- [ ] Код компілюється без помилок
- [ ] Програма запускається та працює коректно
- [ ] Всі документаційні файли створені
- [ ] README.md містить опис проекту
- [ ] .gitignore налаштований
- [ ] Коміт-повідомлення зрозумілі
- [ ] Репозиторій має назву та опис
- [ ] Додано topics/tags

---

## Контакти та ліцензія (опціонально)

Додайте в кінець README.md:

```markdown
## 📄 Ліцензія

MIT License - вільне використання в освітніх цілях.

## 👨‍💻 Автор

Студент [Ваше ім'я]  
Група [Номер групи]  
Університет [Назва]

## 📧 Контакти

- GitHub: [@YOUR_USERNAME](https://github.com/YOUR_USERNAME)
- Email: your.email@example.com
```

---

**Успішної публікації! 🚀**

