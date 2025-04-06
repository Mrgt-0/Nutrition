# Nutrition

 Nutrition — это веб-приложение, которое помогает пользователям отслеживать их дневное потребление калорий и учет съеденных блюд. С помощью этого приложения пользователи могут легко контролировать свою диету, планировать приемы пищи и достигать своих целей по питанию.

## Функции

- **Регистрация пользователей**: Позволяет пользователям создать учетную запись и управлять своей информацией.
- **Учёт съеденных блюд**: Пользователи могут добавлять блюда, и указывать их калорийность.
- **Учёт приемов пищи**: Пользователи могут создавать приемы пищи, добавлять в них блюда и программа расчитает общую калорийность.
- **Отслеживание калорий**: Приложение рассчитывает и отображает ежедневную норму калорий, а также белков, жиров и углеводов.
- **Анализ массы тела**: Расчет дневной нормы калорий и бжу, расчет индекса массы тела и идеального веса на основе введенных данных.
- **Анализ питания**: Подсказки и статистика о потреблении калорий на основе введенных данных.
- **Отчеты**: Отчет за день с суммой всех калорий, история питания по дням.
- **Безопасность**: Защита данных пользователей и безопасная аутентификация.

## Технологии

- **Backend**: Java, Spring Boot, Spring Data JPA
- **База данных**: PostgreSQL
- **Инструменты разработки**: Maven, Git
- **Системы контроля версий**: GitHub
- **Развертывание**: Docker


## Установка

**Требования**

Для работы с проектом вам понадобятся следующие инструменты:
- [Docker](https://www.docker.com/get-started) (последняя версия)

**1. Клонирование репозитория**

Сначала клонируйте репозиторий на вашу локальную машину

**3. Сборка и запуск приложения**

Для сборки и запуска приложения выполните следующую команду:
docker-compose up --build

**4. Доступ к приложению**

После успешного запуска приложения, оно будет доступно по адресу:
http://localhost:8080

**5. Остановка приложения**

Чтобы остановить контейнеры, используйте:
docker-compose down
