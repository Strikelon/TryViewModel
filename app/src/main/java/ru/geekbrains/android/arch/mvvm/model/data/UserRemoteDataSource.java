package ru.geekbrains.android.arch.mvvm.model.data;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import ru.geekbrains.android.arch.mvvm.model.data.models.ApiUser;
import ru.geekbrains.android.arch.mvvm.model.entities.User;
import ru.geekbrains.android.arch.mvvm.model.network.JsonPlaceHolderApi;

class UserRemoteDataSource implements UserDadaSource {
    private final JsonPlaceHolderApi jsonPlaceHolderApi;

    UserRemoteDataSource(JsonPlaceHolderApi jsonPlaceHolderApi) {
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
    }

    @Override
    public Observable<List<User>> getUsers() {
        return jsonPlaceHolderApi.getJsonplaceholderApiService().getUsers()
                .map(new Function<List<ApiUser>, List<User>>() {
                    @Override
                    public List<User> apply(List<ApiUser> apiUsers) throws Exception {
                        List<User> users = new ArrayList<>(apiUsers.size());

                        for (int i = 0; i < apiUsers.size(); i++) {
                            ApiUser apiUser = apiUsers.get(i);
                            users.add(new User(
                                    i,
                                    apiUser.getName(),
                                    apiUser.getUsername(),
                                    apiUser.getEmail(),
                                    apiUser.getPhone(),
                                    apiUser.getAddress().getCity()));
                        }

                        return users;
                    }
                });
    }
}
