package com.telran.a16_03_20.data.provider.web;

import com.telran.a16_03_20.data.dto.AuthRequestDto;
import com.telran.a16_03_20.data.dto.AuthResponseDto;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRx {
    @POST("api/registration")
    Single<Response<AuthResponseDto>> registration(@Body AuthRequestDto body);

    @POST("api/login")
    Single<Response<AuthResponseDto>> login(@Body AuthRequestDto body);
}
