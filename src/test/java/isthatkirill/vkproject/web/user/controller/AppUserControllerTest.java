package isthatkirill.vkproject.web.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import isthatkirill.vkproject.auth.config.SecurityConfig;
import isthatkirill.vkproject.web.user.dto.AppUserDtoRequest;
import isthatkirill.vkproject.web.user.dto.AppUserDtoResponse;
import isthatkirill.vkproject.web.user.model.Role;
import isthatkirill.vkproject.web.user.service.impl.AppUserServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Kirill Emelyanov
 */

@WebMvcTest(controllers = AppUserController.class)
@Import(SecurityConfig.class)
class AppUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AppUserServiceImpl userService;

    @Test
    @SneakyThrows
    void registerTest() {
        AppUserDtoRequest userDtoRequest = AppUserDtoRequest.builder()
                .username("user's username")
                .password("user's password")
                .build();

        AppUserDtoResponse userDtoResponse = AppUserDtoResponse.builder()
                .username(userDtoRequest.getUsername())
                .password(userDtoRequest.getPassword())
                .id(1L)
                .roles(new ArrayList<>(List.of(Role.ROLE_USERS_EDITOR))).build();

        when(userService.register(any())).thenReturn(userDtoResponse);

        mvc.perform(post("/api/app/register")
                        .content(mapper.writeValueAsString(userDtoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userDtoResponse.getId()))
                .andExpect(jsonPath("$.username").value(userDtoResponse.getUsername()))
                .andExpect(jsonPath("$.password").value(userDtoResponse.getPassword()))
                .andExpect(jsonPath("$.roles[0]").value(userDtoResponse.getRoles().get(0).name()));

        verify(userService, times(1)).register(any(AppUserDtoRequest.class));
    }

    @Test
    @SneakyThrows
    @WithMockUser(roles = "ADMIN")
    void changeUserRolesWithAccessTest() {
        List<Role> roles = List.of(Role.ROLE_USERS_EDITOR);
        Long userId = 1L;

        AppUserDtoResponse userDtoResponse = AppUserDtoResponse.builder()
                .username("username")
                .password("password")
                .id(userId)
                .roles(new ArrayList<>(roles)).build();

        when(userService.changeUserRoles(anyLong(), anyList())).thenReturn(userDtoResponse);

        mvc.perform(put("/api/app/users")
                        .param("userId", String.valueOf(userId))
                        .content(mapper.writeValueAsString(roles))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userDtoResponse.getId()))
                .andExpect(jsonPath("$.username").value(userDtoResponse.getUsername()))
                .andExpect(jsonPath("$.password").value(userDtoResponse.getPassword()))
                .andExpect(jsonPath("$.roles[0]").value(userDtoResponse.getRoles().get(0).name()));

        verify(userService, times(1)).changeUserRoles(userId, roles);
    }

    @Test
    @SneakyThrows
    @WithMockUser(roles = "ALBUMS_EDITOR")
    void changeUserRolesWithoutAccessTest() {
        List<Role> roles = List.of(Role.ROLE_USERS_EDITOR);
        Long userId = 1L;

        mvc.perform(put("/api/app/users")
                        .param("userId", String.valueOf(userId))
                        .content(mapper.writeValueAsString(roles))
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

        verifyNoInteractions(userService);
    }


}