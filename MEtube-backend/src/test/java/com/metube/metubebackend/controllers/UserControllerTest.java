package com.metube.metubebackend.Controller;

import com.metube.metubebackend.controllers.UserController;
import com.metube.metubebackend.controllers.contracts.UserCreateRequest;
import com.metube.metubebackend.controllers.contracts.UserPasswordResetRequest;
import com.metube.metubebackend.entities.UserEntity;
import com.metube.metubebackend.service.UserService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_getUsers(){
        when(userService.getUsers()).thenReturn(new ArrayList<>());
        List<UserEntity> result = userController.getAllUsers();

        assertEquals(0, result.size());
        verify(userService, times(1)).getUsers();
    }

    @Test
    public void test_getUserId(){
        try {
            when(userService.getUserId()).thenReturn("10");
            String result = userController.getUserId();

            assertEquals(result, "10");
            verify(userService, times(1)).getUserId();
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void test_createUser(){
        UserCreateRequest req = new UserCreateRequest("admin", "1234");
        UserEntity user = new UserEntity("10", "1234", "admin", "USER");
        when(userService.createUser(req)).thenReturn(user);

        UserEntity result = userController.createUser(req);

        assertEquals(result, user);
        verify(userService, times(1)).createUser(req);
    }

    @Test
    public void test_updatePassword() throws BadRequestException {
        UserPasswordResetRequest req = new UserPasswordResetRequest("1234");
        UserEntity user = new UserEntity("10", "1234", "admin", "USER");
        when(userService.updateUserPassword("10", req)). thenReturn(user);

        UserEntity result = userController.updateUserPassword("10", req);

        assertEquals(result, user);
        verify(userService, times(1)).updateUserPassword("10", req);
    }

    @Test
    public void test_deleteUser() throws BadRequestException {
        userController.deleteUser("10");

        verify((userService),times(1)).deleteUser("10");
    }
}
