package ru.geekbrains.android.arch.mvvm.model.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.geekbrains.android.arch.mvvm.model.data.models.ApiUser;

public interface JsonplaceholderApiService {

    @GET("users")
    Observable<List<ApiUser>> getUsers();

}
