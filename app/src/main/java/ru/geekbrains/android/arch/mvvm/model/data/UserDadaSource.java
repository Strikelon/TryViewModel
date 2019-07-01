package ru.geekbrains.android.arch.mvvm.model.data;

import java.util.List;

import io.reactivex.Observable;
import ru.geekbrains.android.arch.mvvm.model.entities.User;

public interface UserDadaSource {
    Observable<List<User>> getUsers();
}
