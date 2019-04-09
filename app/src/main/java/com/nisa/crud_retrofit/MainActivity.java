package com.nisa.crud_retrofit;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nisa.crud_retrofit.data.ResponseSiswa;
import com.nisa.crud_retrofit.data.SiswaItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rc;
    ProgressBar progressBar;
    FloatingActionButton fab;

    //untuk menyimpan datanya kedalam list
    List<ResponseSiswa> Respondatasiswa = new ArrayList<>();
    List<SiswaItem> listsiswa = new ArrayList<>();

    //untuk nge import class dari luar ke mainactivity
    ApiService apiService;
    AdapterList adapterList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = ConfigApi.getApiService();
        rc = findViewById(R.id.rc_list);
        progressBar = findViewById(R.id.pb);

        //new layout manager
        //untuk mengatur-ngatur layoutnya , jika tidak memakai nanti layoutnya nggak keluar
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rc.setLayoutManager(layoutManager);

        //set adapter
        adapterList = new AdapterList(listsiswa, getApplicationContext());
        rc.setAdapter(adapterList);

        //untuk mengelola datanya , mengeset , mengambil
        dataAttachment();

        fab= findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),DetailActivity.class);
                i.putExtra("id", "");
                i.putExtra("nama","");
                i.putExtra("alamat","");
                i.putExtra("hp","");
                i.putExtra("aksi", "tambah");
                startActivity(i);
            }
        });


    }

    private void dataAttachment() {

        //di hapus dulu data lamanya baru ngeluarin data baru, supaya nggak numpuk
        Respondatasiswa.clear();
        listsiswa.clear();
        apiService.readData().enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                if (response.isSuccessful()){
                try {
                    //kalau ada 'body' nya dia berarti ngambil semua data respon siswa
                    int data = response.body().getSiswa().size();

                    for (int a = 0; a < data; a++) {
                        SiswaItem model = new SiswaItem(
                                response.body().getSiswa().get(a).getId(),
                                response.body().getSiswa().get(a).getNama(),
                                response.body().getSiswa().get(a).getAlamat(),
                                response.body().getSiswa().get(a).getHp());
                        listsiswa.add(model);
                    }
                    ResponseSiswa item = new ResponseSiswa(listsiswa);
                    Respondatasiswa.add(item);

                    adapterList = new AdapterList(listsiswa, getApplicationContext());
                    rc.setAdapter(adapterList);

                    if (listsiswa.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Data tidak ada", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MainActivity.this, "Server is down", Toast.LENGTH_SHORT).show();
            }
        }

            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Server is down", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
