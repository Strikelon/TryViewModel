package ru.geekbrains.android.arch.mvvm.model.repositories;

import io.reactivex.Observable;
import ru.geekbrains.android.arch.mvvm.model.entities.User;

public interface UserRepository {
    Observable<User> getUser(int id);
}
