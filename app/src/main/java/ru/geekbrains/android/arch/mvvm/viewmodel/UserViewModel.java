package ru.geekbrains.android.arch.mvvm.viewmodel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.android.arch.mvvm.model.entities.User;
import ru.geekbrains.android.arch.mvvm.model.interactors.UserModel;

/**
 * Наследуемся от базового класса ViewModel
 */
public class UserViewModel extends ViewModel {

    public static final String SAVE_USER_ID = "user_id";

    private final UserModel model;
    private Disposable disposable;

    // поля данных, на которые подписывается View
    private MutableLiveData<User> userLiveData;
    private MutableLiveData<String> errorLiveData;
    private MutableLiveData<String> resultLiveData;

    // идентификатор пользователя на случай пересоздания процесса
    // сохраняется с помощью стандартного механизма Bundle - savedInstanceState
    private int usedId = -1;

    private final Scheduler subscribeOn;
    private final Scheduler observeOn;

    /**
     * Конструктор с scheduler из RxJava
     */
    public UserViewModel(Scheduler subscribeOn, Scheduler observeOn, UserModel model) {
        this.subscribeOn = subscribeOn;
        this.observeOn = observeOn;
        this.model = model;

        this.userLiveData = new MutableLiveData<>();
        this.errorLiveData = new MutableLiveData<>();
        this.resultLiveData = new MutableLiveData<>();
    }

    /**
     * Используется для восстановления идентификатора из savedInstanceState
     */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            usedId = savedInstanceState.getInt(SAVE_USER_ID, -1);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVE_USER_ID, usedId);
    }

    /**
     * Запрашиваем данные, только если они не были получены ранее
     * ViewModel сохранится при пересоздании активити и данные не нужно будет запрашивать вновь
     */
    public void onStart() {
        if (userLiveData.getValue() == null) {
            // получаем данные из модели аналогично MVP
            model.getUser(getUsedId())
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .subscribe(new UserObserver());
        }
    }

    /**
     * генерация идентификатора пользователя для наглядной демонстрации сохрания данных
     */
    private int getUsedId() {
        if (usedId == -1) {
            usedId = model.getUserId();
        }
        return usedId;
    }

    /**
     * Единственный метод жизненного цикла ViewModel
     * Вызывается, когда пользователь явно покидает активити и ViewModel больше не будет хранится
     * явно покидание активити - кнопка "назад"
     * Если пользователь свернул приложение или перевернул экран - активити будет пересоздано,
     * ViewModel сохранится, а этот метод не вызовется
     */
    @Override
    protected void onCleared() {
        if (disposable != null) {
            disposable.dispose();
        }

        super.onCleared();
    }

    public void onUserAction() {
        resultLiveData.setValue("Result");
    }

    public LiveData<User> getUser() {
        return userLiveData;
    }

    public LiveData<String> getError() {
        return errorLiveData;
    }

    public LiveData<String> getResult() {
        return resultLiveData;
    }

    private class UserObserver implements Observer<User> {

        @Override
        public void onSubscribe(Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(User user) {
            // полученные данные передаем в обозреваемое поле, которое уведомит подписчиков
            userLiveData.setValue(user);
        }

        @Override
        public void onError(Throwable e) {
            // ошибку тоже передаем в обозреваемое поле
            errorLiveData.setValue("Error!");
        }

        @Override
        public void onComplete() {

        }
    }
}
