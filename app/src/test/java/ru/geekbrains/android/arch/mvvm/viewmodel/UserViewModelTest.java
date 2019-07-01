package ru.geekbrains.android.arch.mvvm.viewmodel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.android.arch.mvvm.model.entities.User;
import ru.geekbrains.android.arch.mvvm.model.interactors.UserModel;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserViewModelTest {

    private UserViewModel viewModel;

    @Mock
    private
    UserModel userModel;

    @Rule
    public
    TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        viewModel = new UserViewModel(Schedulers.trampoline(), Schedulers.trampoline(), userModel);
    }

    @Test
    public void shouldShowUserOnStartIfSuccess() {
        User testUser = new User(12345, "name", "username", "email", "phone", "city");

        when(userModel.getUserId()).thenReturn(12345);
        when(userModel.getUser(12345)).thenReturn(Observable.just(testUser));

        viewModel.onStart();


        Assert.assertEquals(testUser, viewModel.getUser().getValue());
    }

    @Test
    public void shouldShowErrorOnStartIfGetUserFails() {
        when(userModel.getUserId()).thenReturn(9999);
        when(userModel.getUser(9999)).thenReturn(Observable.<User>error(new Throwable("Error")));

        viewModel.onStart();

        Assert.assertEquals("Error!", viewModel.getError().getValue());

    }
}