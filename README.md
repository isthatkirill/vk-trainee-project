## Практическое задание VK

### Функционал и используемые технологии

- Базовая авторизация с помощью Spring Security
- Проксирование GET, POST, PUT, DELETE запросов к https://jsonplaceholder.typicode.com/ с помощью WebClient
- Ролевая модель доступа
- REST API для создания пользователей приложения и выдачи им ролей
- Ведение аудита действий с использованием PostreSQL и Spring Data JPA
- Тестирование с помощью JUnit и Mockito
- Redis-кэш для GET-запросов
- Контейнеризация с помощью Docker

### Инструкция по запуску

1. Склонируйте репозиторий `git clone https://github.com/isthatkirill/vk-trainee-project.git`

2. Перейдите в директорию с проектом `cd vk-trainee-project`

3. Соберите проект `mvn clean package`

4. Запустите Redis локально на компьютере с параметрами, указанными в `application.yaml` или воспользуйтесь заранее подготовленным в `docker-compose.yaml` контейнером c помощью команды `docker compose up`

5. Запустите SpringBoot-приложение `mvn spring-boot:run`

6. Отправьте подготовленные в [postman-коллекции](https://github.com/isthatkirill/vk-trainee-project/blob/main/postman/requests.json) запросы

7. В ветке [add-docker](https://github.com/isthatkirill/vk-trainee-project/tree/add-docker) приложение полностью контейнеризированно. Для запуска достаточно перейти в директорию с проектом и выполнить команду `docker compose up`
