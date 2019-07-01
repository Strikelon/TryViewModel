package ru.geekbrains.android.arch.mvvm.model.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiCompany {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("catchPhrase")
    @Expose
    public String catchPhrase;
    @SerializedName("bs")
    @Expose
    public String bs;

}