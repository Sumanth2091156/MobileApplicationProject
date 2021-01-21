package com.pizzaworldnew;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface api {
    @FormUrlEncoded
    @POST("/register")
    Call<ResponseBody> register(@Field("fname") String fname,
                                @Field("lname") String lname,
                                @Field("phone") String phone,
                                @Field("email") String email,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("/login")
    Call<ResponseBody> login(@Field("email") String email,@Field("password") String password);

    @GET("/getpizza")
    Call<List<pizzaModel>> getitems();

    @FormUrlEncoded
    @POST("/adminlogin")
    Call<ResponseBody> adminlogin(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("/addpizza")
    Call<ResponseBody> addpizza(@Field("name") String name,
                                @Field("price") String price,
                                @Field("image") String image,
                                @Field("description") String desc);

    @FormUrlEncoded
    @POST("/feedback")
    Call<ResponseBody> feedback(@Field("title") String title,
                                @Field("description") String desc);

}
