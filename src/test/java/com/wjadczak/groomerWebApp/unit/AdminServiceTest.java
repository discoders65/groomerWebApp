package com.wjadczak.groomerWebApp.unit;

import com.wjadczak.groomerWebApp.TestUtils;
import com.wjadczak.groomerWebApp.dto.UserDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.errors.AppointmentNotFoundException;
import com.wjadczak.groomerWebApp.errors.EntityNotFoundException;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.repository.ImageRepository;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import com.wjadczak.groomerWebApp.service.implementation.AdminServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private ImageRepository imageRepositoryMock;
    @Mock
    private AppointmentRepository appointmentRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private AdminServiceImpl adminService;
    @Test
    void shouldThrowInvalidSearchRequestExceptionIfProvidedInvalidUserId(){
        // given
        UUID testId = TestUtils.NON_EXSITENT_USER_ID;
        // when
        when(userRepositoryMock.findById(testId)).thenReturn(Optional.empty());
        // then
        Assertions.assertThrows(InvalidSearchRequestException.class, () -> adminService.getUserById(testId));
    }
    @Test
    void shouldReturnListOfUserDto(){
        // given
        List<UserEntity> testUserList = List.of(TestUtils.TEST_USER);
        // when
        when(userRepositoryMock.findAll()).thenReturn(testUserList);
        // then
        List<UserDto> result = adminService.getAllUsers();
        Assertions.assertEquals(testUserList.size(), result.size());
    }
    @Test
    void shouldConfirmAppointmentIfGivenProperId(){
        // given
        UUID testId = TestUtils.NON_EXISTENT_APPOINTMENT_ID;
        AppointmentEntity testAppointment = new AppointmentEntity();
        testAppointment.setId(testId);
        // when
        when(appointmentRepositoryMock.findById(testId)).thenReturn(Optional.of(testAppointment));
        // then
        adminService.confirmAppointment(testId);
        assertTrue(testAppointment.isAccepted());
        verify(appointmentRepositoryMock, times(1)).findById(testId);
        verify(appointmentRepositoryMock, times(1)).save(testAppointment);
    }
    @Test
    void shoudThrowAppointmentNotFoundExceptionIfAppointmentIdNotFound(){
        // given
        UUID testId = TestUtils.NON_EXISTENT_APPOINTMENT_ID;
        // when
        when(appointmentRepositoryMock.findById(testId)).thenReturn(Optional.empty());
        // then
        Assertions.assertThrows(AppointmentNotFoundException.class, () -> adminService.confirmAppointment(testId));
    }
    @Test
    void shouldThrowEntityNotFoundExceptionIfImageIdNotFound(){
        // given
        UUID testId = TestUtils.NON_EXSITENT_IMAGE_ID;
        // when
        when(imageRepositoryMock.findById(testId)).thenReturn(Optional.empty());
        // then
        Assertions.assertThrows(EntityNotFoundException.class, () -> adminService.downloadImage(testId));
    }
    @Test
    void shouldCancelAppointmentIfGivenProperId(){
        // given
        UUID testId = TestUtils.NON_EXISTENT_APPOINTMENT_ID;
        AppointmentEntity testAppointment = new AppointmentEntity();
        testAppointment.setId(testId);
        // when
        when(appointmentRepositoryMock.findById(testId)).thenReturn(Optional.of(testAppointment));
        // then
        adminService.cancelAppointment(testId);
        assertTrue(testAppointment.isCancelled());
        verify(appointmentRepositoryMock, times(1)).findById(testId);
        verify(appointmentRepositoryMock, times(1)).save(testAppointment);
    }
}
