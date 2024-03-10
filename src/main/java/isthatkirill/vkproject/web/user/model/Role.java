package isthatkirill.vkproject.web.user.model;

/**
 * @author Kirill Emelyanov
 */

public enum Role {

    DEFAULT, //доступ только к просмотру постов (выдается по умолчанию)
    ROLE_ADMIN, //доступ ко всем эндпоинтам
    ROLE_POSTS, //доступ к эндпоинтам постов
    ROLE_USERS, //доступ к эндпоинтам пользователей
    ROLE_ALBUMS //доступ к эндпоинтам альбомов

}
