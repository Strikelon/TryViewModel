package ru.geekbrains.android.arch.mvvm.model.interactors;

import java.util.Random;

import io.reactivex.Observable;
import ru.geekbrains.android.arch.mvvm.model.entities.User;
import ru.geekbrains.android.arch.mvvm.model.repositories.UserRepository;

public class UserModelImpl implements UserModel {
    private final UserRepository userRepository;

    public UserModelImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Observable<User> getUser(int id) {
        return userRepository.getUser(id);
    }

    @Override
    public int getUserId() {
        Random random = new Random();
        return random.nextInt(10);
    }
}
