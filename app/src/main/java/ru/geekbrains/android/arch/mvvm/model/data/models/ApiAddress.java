package ru.geekbrains.android.arch.mvvm.model.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiAddress {

    @SerializedName("street")
    @Expose
    String street;
    @SerializedName("suite")
    @Expose
    String suite;
    @SerializedName("city")
    @Expose
    String city;
    @SerializedName("zipcode")
    @Expose
    String zipcode;
    @SerializedName("geo")
    @Expose
    ApiGeo geo;

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public ApiGeo getGeo() {
        return geo;
    }
}