package isthatkirill.vkproject.web.user.model;

/**
 * @author Kirill Emelyanov
 */

public enum Role {

    DEFAULT, // по дефолту у пользователя не будет доступа к чему либо (роли выдаются админом)
    ROLE_ADMIN, //доступ ко всем эндпоинтам
    ROLE_POSTS_VIEWER, //доступ к просмотру постов
    ROLE_POSTS_EDITOR, //доступ к редактированию и созданию постов
    ROLE_USERS_VIEWER, //доступ к просмотру пользователей
    ROLE_USERS_EDITOR, //доступ к редактированию и созданию пользователей
    ROLE_ALBUMS_VIEWER, //доступ к просмотру альбомов
    ROLE_ALBUMS_EDITOR //доступ к редактированию и созданию альбомов

}
