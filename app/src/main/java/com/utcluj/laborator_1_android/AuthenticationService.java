package com.utcluj.laborator_1_android;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Student on 12/12/2017.
 */
public interface AuthenticationService {
    @HTTP(method = "DELETE", path = "logout.php", hasBody = true)
    @Headers("Content-Type: application/json")
    Call<Void> logoutUser (@Body Credentials credentials);





    @POST("authenticate.php")
    @Headers("Content-Type: application/json")
    Call<User> loginUser(@Body Credentials credentials);


}
