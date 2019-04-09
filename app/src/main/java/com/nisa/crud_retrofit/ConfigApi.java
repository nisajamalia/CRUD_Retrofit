package com.nisa.crud_retrofit;

public class ConfigApi {

    public static final String BASE_URL = "HTTP://192.168.100.30/server_siswa/";

    public static ApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }

}
