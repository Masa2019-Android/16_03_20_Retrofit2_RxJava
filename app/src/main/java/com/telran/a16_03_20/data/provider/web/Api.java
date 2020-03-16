package com.telran.a16_03_20.data.provider.web;

import com.telran.a16_03_20.data.dto.AuthRequestDto;
import com.telran.a16_03_20.data.dto.AuthResponseDto;
import com.telran.a16_03_20.data.dto.ContactDto;
import com.telran.a16_03_20.data.dto.ContactListDto;
import com.telran.a16_03_20.data.dto.DeleteResponseDto;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @POST("api/registration")
    Call<AuthResponseDto> registration(@Body AuthRequestDto body);

    @POST("api/login")
    Call<AuthResponseDto> login(@Body AuthRequestDto body);

    @DELETE("api/clear")
    Call<DeleteResponseDto> clear(@Header("Authorization")String token);

    @DELETE("api/contact/{id}")
    Call<DeleteResponseDto> deleteById(@Path("id")long id);

    @GET("api/contact")
    Call<ContactListDto> getAllContacts(@Header("Authorization")String token);

    @POST("api/contact")
    Call<ContactDto> addContact(@Header("Authorization") String token, @Body ContactDto contact);

    @PUT("api/contact")
    Call<ContactDto> updateContact(@Header("Authorization")String token,@Body ContactDto contact);
}
