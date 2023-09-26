package com.dzniox.travel_mate.network;

import com.dzniox.travel_mate.network.events.APIEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.nio.charset.Charset;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class APIClient {

    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    ResponseBody responseBody = response.body();
                    BufferedSource source = responseBody.source();
                    source.request(Long.MAX_VALUE); // Buffer the entire body.
                    Buffer buffer = source.buffer();
                    String respData = buffer.clone().readString(Charset.defaultCharset());
                    JSONObject resp = null;
                    try {
                        resp = new JSONObject(respData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (resp != null) {
                        switch (resp.optInt(APIConstants.Params.ERROR_CODE)) {
                            case APIConstants.ErrorCodes.TOKEN_EXPIRED:
                                emitEvent(APIConstants.ErrorCodes.TOKEN_EXPIRED, resp.optString(APIConstants.Params.ERROR_MSG));
                                break;

                            case APIConstants.ErrorCodes.USER_DOESNT_EXIST:
                                emitEvent(APIConstants.ErrorCodes.USER_DOESNT_EXIST, resp.optString(APIConstants.Params.ERROR_MSG));
                                break;

                            case APIConstants.ErrorCodes.USER_RECORD_DELETED_CONTACT_ADMIN:
                                emitEvent(APIConstants.ErrorCodes.USER_RECORD_DELETED_CONTACT_ADMIN, resp.optString(APIConstants.Params.ERROR_MSG));
                                break;

                            case APIConstants.ErrorCodes.INVALID_TOKEN:
                                emitEvent(APIConstants.ErrorCodes.INVALID_TOKEN, resp.optString(APIConstants.Params.ERROR_MSG));
                                break;

                            case APIConstants.ErrorCodes.USER_LOGIN_DECLINED:
                                emitEvent(APIConstants.ErrorCodes.USER_LOGIN_DECLINED, resp.optString(APIConstants.Params.ERROR_MSG));
                                break;

                            case APIConstants.ErrorCodes.ID_TOKEN_REQUIRED:
                                emitEvent(APIConstants.ErrorCodes.ID_TOKEN_REQUIRED, resp.optString(APIConstants.Params.ERROR_MSG));
                                break;

                            case APIConstants.ErrorCodes.NOT_VERIFIED:
                                emitEvent(APIConstants.ErrorCodes.NOT_VERIFIED, resp.optString(APIConstants.Params.ERROR_MSG));
                                break;
                        }
                    }
                    return response;
                }).build();

        return new Retrofit.Builder()
                .baseUrl(APIConstants.URLs.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();
    }

    private static void emitEvent(int code, String message) {
        EventBus.getDefault().post(new APIEvent(message, code));
    }
}
