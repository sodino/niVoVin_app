package com.niVoVin.tasty.api;

import com.niVoVin.tasty.api.bean.Gallery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/6/24.
 */

public interface TastyApi {
    @GET("list.js/{lastIdx}/{size}/")
    Call<List<Gallery>> listGallery(@Path("lastIdx") long lastIdx, @Path("size") int size);
}
