используется Java 11 версии

Сервис для поиска групп, доступны следубщие опции:

1) Поиск по подстроке среди своих групп, с последующим сохранением деталей поиска
   Post запрос http://localhost:8079/test/my_groups_search
2) Поиск по подстроке среди своих групп и групп друзей
   Post запрос http://localhost:8079/test/groups_search
3) Вывод деталей поиска
   http://localhost:8079/test/get_upload_details?page=0&size=5
   в переменной page указывается страница запроса, в size количество выводимых записей в странице

Инструкция по запуску:

Заходим в application.properties, выставляем настройки для подключения к БД,
id пользователя в ВК и ключ безопасности, после этого программа готова для использования.

| Имя параметра | Описание                             |
| --- |--------------------------------------|
| user_id | Id пользователя ВКонтакте            |
| access_token | Секретный ключ для работы приложения |

Инструкция по получению секретного ключа:
https://vk.com/dev/access_token

После запуска приложения доступна сгенерированная документация по API по ссылке
http://localhost:8079/swagger-ui/#/

Идеи по развитию:
1) Добавление новых функций по работе с API вк
2) Сохранение в БД результата поиска среди своих групп и групп друзей
3) Улучшение таблицы с деталями поиска в сторону большей функуиональности и информативности
4) Покрытие тестами
