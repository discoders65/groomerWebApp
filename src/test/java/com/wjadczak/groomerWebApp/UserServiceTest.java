package com.wjadczak.groomerWebApp;

import com.wjadczak.groomerWebApp.repository.UserRepository;
import com.wjadczak.groomerWebApp.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldThrowUsernameNotFoundExceptionIfNoUserFound(){
    // when
        when(userRepositoryMock.findByEmail(anyString())).thenReturn(Optional.empty());
    // then
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.userDetailsService().loadUserByUsername(TestUtils.NON_EXITENT_EMAIL));

    }
}
