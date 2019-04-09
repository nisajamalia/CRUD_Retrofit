package com.nisa.crud_retrofit;

import com.nisa.crud_retrofit.data.ResponseSiswa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("datasiswa.php")
    Call<ResponseSiswa> readData();

    @FormUrlEncoded
    @POST("tambah_siswa.php")
    Call<ResponseSiswa> addData(@Field("nama_siswa") String nama,
                                @Field("alamat_siswa")String alamat,
                                @Field("hp_siswa")String hp);

    @FormUrlEncoded
    @POST("edit_siswa.php")
    Call<ResponseSiswa> editData(@Field("id_siswa")String id,
                                 @Field("nama_siswa") String nama,
                                 @Field("alamat_siswa")String alamat,
                                 @Field("hp_siswa")String hp);

    @FormUrlEncoded
    @POST("hapus_siswa.php")
    Call<ResponseSiswa> hapusData(@Field("id_siswa")String id);
}
