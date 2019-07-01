package ru.geekbrains.android.arch.mvvm.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.android.arch.mvvm.model.data.UserRepositoryImpl;
import ru.geekbrains.android.arch.mvvm.model.interactors.UserModelImpl;
import ru.geekbrains.android.arch.mvvm.model.network.JsonPlaceHolderApi;
import ru.geekbrains.android.arch.mvvm.model.repositories.UserRepository;
import ru.geekbrains.android.arch.mvvm.viewmodel.UserViewModel;

/**
 * Фабрика для создания ViewModel с нестандартным конструктором
 * Если ваша ViewModel не использует параметры конструктора фабрика не нужна
 */
public class UserViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        JsonPlaceHolderApi jsonPlaceHolderApi = new JsonPlaceHolderApi();
        UserRepository userRepository = new UserRepositoryImpl(jsonPlaceHolderApi);
        UserModelImpl userModel = new UserModelImpl(userRepository);

        if (modelClass == UserViewModel.class) {
            return (T) new UserViewModel(Schedulers.io(), AndroidSchedulers.mainThread(), userModel);
        } else {
            throw new IllegalArgumentException("model class: " + modelClass);
        }

    }

}
