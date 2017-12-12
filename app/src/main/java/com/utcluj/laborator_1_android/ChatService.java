package com.utcluj.laborator_1_android;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;

/**
 * Created by Student on 12/12/2017.
 */
public interface ChatService {

    @GET("readmessages.php")
    @Headers("Content-Type: application/json")
    Call<MessageList> getMessages(@Header(value = "Authorization") String auth);

    @PUT("sendmessage.php")
    @Headers("Content-Type: application/json")
    Call<Void> putMessages(@Header(value = "Authorization") String auth,@Body Message message);
}
