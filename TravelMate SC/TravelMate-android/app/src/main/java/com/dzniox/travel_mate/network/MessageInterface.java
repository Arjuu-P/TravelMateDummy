package com.dzniox.travel_mate.network;

import static com.dzniox.travel_mate.network.APIConstants.APIs;
import static com.dzniox.travel_mate.network.APIConstants.Params;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MessageInterface {

    @GET(APIs.LOGIN)
    Call<String> sendMessage(
            @Query(Params.EMAIL) String user,
            @Query(Params.PASSWORD) String password,
            @Query(Params.MOBILE) String mobile,
            @Query(Params.MESSAGE) String message,
            @Query(Params.SENDER) String sender,
            @Query(Params.TYPE) String type,
            @Query(Params.TEMPLATE_ID) String template_id
    );

}

