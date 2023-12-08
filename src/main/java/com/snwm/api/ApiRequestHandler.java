package com.snwm.api;

import java.io.IOException;

import com.google.gson.Gson;
import com.snwm.enums.ErrorCode;
import com.snwm.exception.ApiException;
import com.snwm.objects.ErrorResponse;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RequiredArgsConstructor
public class ApiRequestHandler {
    private final OkHttpClient client;
    private final Gson gson;

    public <T> T execute(Request request, Class<T> responseClass) {
        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }

            return gson.fromJson(responseBody, responseClass);
        } catch (IOException e) {
            throw new ApiException(ErrorCode.ERROR_OKHTTP);
        }
    }
}
