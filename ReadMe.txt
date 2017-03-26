Проект по тестовому заданию "технологии надежности"

Задание:
Eсть 3 таблицы user(id, name, password, active, employee_id), employee(id, full_name, short_name, age, city), skils(id, employee_id, name, description). Необходимо написать приложение которое из БД достанет список сотудников с навыками и составить отчет в excel на первой странице, на второй список городов и по ним уникальные навыки сотрудников

Технологии:
spring boot, spring data, flyway, apache poi, для загрузки файла сделать веб интерфейс

При разработке использовалась PostgreSQL 9.6
Настройки БД в src/main/resources/application.properties
Веб интерфейс доступен http://localhost:8080/
Можно добавлять employee, user, skils
Также для теста загружены некоторые данные.
