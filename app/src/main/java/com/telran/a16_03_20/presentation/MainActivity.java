package com.telran.a16_03_20.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.telran.a16_03_20.R;
import com.telran.a16_03_20.data.dto.AuthRequestDto;
import com.telran.a16_03_20.data.dto.AuthResponseDto;
import com.telran.a16_03_20.data.dto.ErrorResponseDto;
import com.telran.a16_03_20.data.provider.web.Api;
import com.telran.a16_03_20.data.provider.web.ApiRx;
import com.telran.a16_03_20.di.ApiProvider;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputEmail, inputPassword;
    Button loginBtn, regBtn;
    ProgressBar myProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        loginBtn = findViewById(R.id.loginBtn);
        regBtn = findViewById(R.id.regBtn);

        myProgress = findViewById(R.id.myProgress);

        loginBtn.setOnClickListener(this);
        regBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginBtn){
//            makeLogin();
            makeRxLogin();
        }else if(v.getId() == R.id.regBtn){
//            makeRegistration();
            makeRxRegistration();
        }
    }

    private void showProgress(boolean isShow){
        myProgress.setVisibility(isShow?View.VISIBLE:View.INVISIBLE);
        regBtn.setEnabled(!isShow);
        loginBtn.setEnabled(!isShow);
        inputPassword.setEnabled(!isShow);
        inputEmail.setEnabled(!isShow);
    }

    private void makeRxRegistration(){
        ApiRx apiRx = ApiProvider.getInstance().getApiRx();
        AuthRequestDto requestDto = new AuthRequestDto(inputEmail.getText().toString(),inputPassword.getText().toString());
        showProgress(true);
        Disposable disposable = apiRx.registration(requestDto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response, error) -> {
                    showProgress(false);
                    if(response != null) {
                        if (response.isSuccessful()) {
                            Log.d("MY_TAG", "makeRxRegistration success: " + response.body());
                        } else {
                            Log.d("MY_TAG", "makeRxRegistration error: " + response.errorBody().string());
                        }
                    }else if(error != null){
                        error.printStackTrace();
                    }
                    Log.d("MY_TAG", "makeRxRegistration: " + error);
                });
    }

    private void makeRxLogin(){
        AuthRequestDto requestDto = new AuthRequestDto(inputEmail.getText().toString(),inputPassword.getText().toString());
        showProgress(true);
        Disposable disposable = ApiProvider.getInstance().getApiRx()
                .login(requestDto)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                    showProgress(false);
                    if(response.isSuccessful()){
                        AuthResponseDto responseDto = response.body();
                        Log.d("MY_TAG", "makeRxLogin success: " + responseDto);
                    }else{
                        Log.d("MY_TAG", "makeRxLogin error: " + response.errorBody().string());
                    }
                },error -> {
                    showProgress(false);
                    error.printStackTrace();
                });
    }

    private void makeRegistration() {
        Api api = ApiProvider.getInstance().getApi();
        AuthRequestDto requestDto = new AuthRequestDto(inputEmail.getText().toString(),inputPassword.getText().toString());
        final Call<AuthResponseDto> call = api.registration(requestDto);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<AuthResponseDto> response = call.execute();
                    if(response.isSuccessful()){
                        AuthResponseDto dto = response.body();
                        Log.d("MY_TAG", "reg success: " + dto);
                    }else if(response.code() == 400 || response.code() == 409){
                        String error = response.errorBody().string();
                        Log.d("MY_TAG", "error: " + error);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void makeLogin() {
        showProgress(true);
        AuthRequestDto requestDto = new AuthRequestDto(inputEmail.getText().toString(),inputPassword.getText().toString());
        Call<AuthResponseDto> call = ApiProvider.getInstance().getApi().login(requestDto);

        call.enqueue(new Callback<AuthResponseDto>() {
            @Override
            public void onResponse(Call<AuthResponseDto> call, Response<AuthResponseDto> response) {
                showProgress(false);
                if(response.isSuccessful()){
                    AuthResponseDto responseDto = response.body();
                    Log.d("MY_TAG", "onResponse success: " + responseDto);
                }else{
                    try {
                        Log.d("MY_TAG", "onResponse error: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponseDto> call, Throwable t) {
                showProgress(false);
                t.printStackTrace();
            }
        });

    }
}
