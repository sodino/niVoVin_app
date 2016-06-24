package com.niVoVin.tasty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.niVoVin.tasty.adapter.StaggeredGridAdapter;
import com.niVoVin.tasty.api.TastyApi;
import com.niVoVin.tasty.api.bean.Gallery;
import com.niVoVin.tasty.constant.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int GRID_NUMBER = 2;

    private RecyclerView recyclerView;
    private StaggeredGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGet = (Button) findViewById(R.id.btnGet);
        btnGet.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layManager = new StaggeredGridLayoutManager(GRID_NUMBER, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new StaggeredGridAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnGet:{
                getGalleryList();
            }
            break;
        }
    }


    public void getGalleryList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TastyApi tastyApi = retrofit.create(TastyApi.class);
        Call<List<Gallery>> call = tastyApi.listGallery(0, 100);
        Log.d("niVoVin", "call listGallery...");
        call.enqueue(new Callback<List<Gallery>>() {
            @Override
            public void onResponse(Call<List<Gallery>> call, Response<List<Gallery>> response) {
                List<Gallery> listGallery = response.body();
                Log.d("niVoVin", "onResponse() gallery.size=" + listGallery.size());
                for (Gallery gallery : listGallery) {
                    String title = gallery.title;
                    Log.d("niVoVin", gallery.index + " " + title);
                }

                adapter.addGallerys(listGallery);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Gallery>> call, Throwable t) {
                Log.d("niVoVin", "onFailure() " + t.toString());
            }
        });
    }
}
