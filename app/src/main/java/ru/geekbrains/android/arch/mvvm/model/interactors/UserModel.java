package ru.geekbrains.android.arch.mvvm.model.interactors;

import io.reactivex.Observable;
import ru.geekbrains.android.arch.mvvm.model.entities.User;

public interface UserModel {
    Observable<User> getUser(int id);
    int getUserId();
}
