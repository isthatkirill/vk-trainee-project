package isthatkirill.vkproject.web.user.model;

/**
 * @author Kirill Emelyanov
 */

public enum Role {

    DEFAULT, // по дефолту у пользователя не будет доступа к чему либо (роли выдаются админом)
    ROLE_ADMIN, //доступ ко всем эндпоинтам
    ROLE_POSTS, //доступ к эндпоинтам постов
    ROLE_USERS, //доступ к эндпоинтам пользователей
    ROLE_ALBUMS //доступ к эндпоинтам альбомов

}
