package com.example.b07_project;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.example.b07_project.model.RegisterModel;
import com.example.b07_project.model.RegisterModelImpl;
import com.example.b07_project.presenter.RegisterPresenter;
import com.example.b07_project.view.RegisterView;

public class RegisterPresenterTest {

    @Mock
    private RegisterModelImpl mockModel;

    @Mock
    private RegisterView mockView;

    @InjectMocks
    private RegisterPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new RegisterPresenter(mockModel, mockView);
    }

    @Test
    public void testCreateAdminUserSuccess() {
        // Arrange
        String validEmail = "valid@example.com";
        String validPassword = "password";
        boolean isAdmin = true;

        // Simulate a successful admin registration
        doAnswer(invocation -> {
            // Simulate the OnAdminRegister callback
            ((RegisterModel.OnRegisterListener) invocation.getArgument(2)).OnAdminRegister(isAdmin);
            return null;
        }).when(mockModel).createUserAndPass(anyString(), anyString(), any(RegisterModel.OnRegisterListener.class));

        // Act
        presenter.CreateUserAndPassword(validEmail, validPassword, isAdmin);

        // Assert
        verify(mockView).ShowAdminRegisterSuccess(isAdmin);
    }

    @Test
    public void testCreateStudentUserSuccess() {
        // Arrange
        String validEmail = "valid@example.com";
        String validPassword = "password";
        boolean isAdmin = false;

        // Simulate a successful student registration
        doAnswer(invocation -> {
            // Simulate the OnStudentRegister callback
            ((RegisterModel.OnRegisterListener) invocation.getArgument(2)).OnStudentRegister();
            return null;
        }).when(mockModel).createUserAndPass(anyString(), anyString(), any(RegisterModel.OnRegisterListener.class));

        // Act
        presenter.CreateUserAndPassword(validEmail, validPassword, isAdmin);

        // Assert
        verify(mockView).ShowStudentRegisterSuccess();
    }

    @Test
    public void testCreateUserFailedRegister() {
        // Arrange
        String invalidEmail = "invalid@example.com";
        String invalidPassword = "wrongpassword";
        boolean isAdmin = false;

        // Simulate a failed registration
        doAnswer(invocation -> {
            // Simulate the OnFailedRegister callback
            ((RegisterModel.OnRegisterListener) invocation.getArgument(2)).OnFailedRegister();
            return null;
        }).when(mockModel).createUserAndPass(anyString(), anyString(), any(RegisterModel.OnRegisterListener.class));

        // Act
        presenter.CreateUserAndPassword(invalidEmail, invalidPassword, isAdmin);

        // Assert
        verify(mockView).ShowRegisterFailed();
    }
}