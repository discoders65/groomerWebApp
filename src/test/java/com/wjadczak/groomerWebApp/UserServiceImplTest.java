package com.wjadczak.groomerWebApp;

import com.wjadczak.groomerWebApp.repository.UserRepository;
import com.wjadczak.groomerWebApp.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class UserServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private UserServiceImpl userService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowUsernameNotFoundExceptionIfNoUserFound(){
    // given
        String testEmail = "nonexistent@nonexistent.com";

    // when
        when(userRepositoryMock.findByEmail(anyString())).thenReturn(Optional.empty());

    // then
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.userDetailsService().loadUserByUsername(testEmail));

    }
}
