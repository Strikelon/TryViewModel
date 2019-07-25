package ru.geekbrains.android.arch.mvvm.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import ru.geekbrains.android.arch.mvvm.R;
import ru.geekbrains.android.arch.mvvm.databinding.UserActivityBinding;
import ru.geekbrains.android.arch.mvvm.model.entities.User;
import ru.geekbrains.android.arch.mvvm.viewmodel.UserViewModel;

public class UserActivity extends FragmentActivity {

    //testcoment
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // фабрика для создания ViewModel с конструктором с параметрами
        UserViewModelFactory userViewModelFactory = new UserViewModelFactory();
        // механизм получения ViewModel
        // можно получить для активити или фрагмента
        viewModel = ViewModelProviders.of(this, userViewModelFactory).get(UserViewModel.class);

        super.onCreate(savedInstanceState);

        // ViewModel не хранит данные при уничтожении процесса приложения
        viewModel.onCreate(savedInstanceState);

        // DataBinding такой же, как в примере обычного MVVM
        UserActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.user_activity);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        viewModel.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();

        // у ViewModel нет событий жизненного цикла на создание
        viewModel.onStart();
    }
}
