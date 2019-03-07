package com.example.mouth.api;

import com.example.mouth.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService
{
    @GET
    Observable<Bean> shou (@Url String url);
}
