package com.snwm.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.snwm.enums.Country;
import com.snwm.enums.ErrorCode;
import com.snwm.enums.ProxyState;
import com.snwm.enums.ProxyType;
import com.snwm.enums.ProxyVersion;
import com.snwm.exception.ApiException;
import com.snwm.objects.BuyResponse;
import com.snwm.objects.CheckResponse;
import com.snwm.objects.DeleteResponse;
import com.snwm.objects.ErrorResponse;
import com.snwm.objects.GetCountResponse;
import com.snwm.objects.GetCountryResponse;
import com.snwm.objects.GetPriceResponse;
import com.snwm.objects.GetProxyResponse;
import com.snwm.objects.IpAuthResponse;
import com.snwm.objects.ProlongResponse;
import com.snwm.objects.SetDescrResponse;
import com.snwm.objects.SetTypeResponse;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RequiredArgsConstructor
public class Proxy6NetApi {
    private static final String API_URL = "https://proxy6.net/api/";

    private static final String GET_PRICE = "/getprice";
    private static final String GET_COUNT = "/getcount";
    private static final String GET_COUNTRY = "/getcountry";
    private static final String GET_PROXY = "/getproxy";
    private static final String SET_TYPE = "/settype";
    private static final String SET_DESCR = "/setdescr";
    private static final String BUY_PROXY = "/buy";
    private static final String PROLONG_PROXY = "/prolong";
    private static final String DELETE = "/delete";
    private static final String CHECK = "/check";
    private static final String IP_AUTH = "/ipauth";

    private final String apiKey;
    private final OkHttpClient client;
    private final Gson gson = new Gson();

    public GetPriceResponse getPrice(int count, int period, ProxyVersion version) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_PRICE);
        urlBuilder
                .append("?count=")
                .append(count)
                .append("&period=")
                .append(period);

        if (version != null) {
            urlBuilder.append("&version=")
                    .append(version.getCode());
        }

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }

            return gson.fromJson(responseBody, GetPriceResponse.class);
        }
    }

    public GetPriceResponse getPrice(int count, int period) throws IOException {
        return getPrice(count, period, ProxyVersion.IPV6);
    }

    public GetCountResponse getCount(Country country, ProxyVersion version) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_COUNT);
        urlBuilder
                .append("?country=")
                .append(country.getCode());

        if (version != null) {
            urlBuilder.append("&version=")
                    .append(version.getCode());
        }

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }

            return gson.fromJson(responseBody, GetCountResponse.class);
        }
    }

    public GetCountResponse getCount(Country country) throws IOException {
        return getCount(country, ProxyVersion.IPV6);
    }

    public GetCountryResponse getCountry(ProxyVersion version) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_COUNTRY);
        urlBuilder.append("?version=")
                .append(version.getCode());

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }

            return gson.fromJson(responseBody, GetCountryResponse.class);
        }
    }

    public GetCountryResponse getCountry() throws IOException {
        return getCountry(ProxyVersion.IPV6);
    }

    public GetProxyResponse getProxy(ProxyState state, String descr, Integer page, Integer limit)
            throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_PROXY);

        if (state != null) {
            urlBuilder.append("?state=").append(state.getCode());
        } else {
            urlBuilder.append("?state=").append(ProxyState.ALL.getCode());
        }

        if (descr != null) {
            urlBuilder.append("&descr=").append(descr);
        }

        if (page != null) {
            urlBuilder.append("&page=").append(page);
        } else {
            urlBuilder.append("&page=1");
        }

        if (limit != null) {
            urlBuilder.append("&limit=").append(limit);
        } else {
            urlBuilder.append("&limit=1000");
        }

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }

            return gson.fromJson(responseBody, GetProxyResponse.class);
        }
    }

    public GetProxyResponse getProxy() throws IOException {
        return getProxy(null, null, null, null);
    }

    public GetProxyResponse getProxy(ProxyState state) throws IOException {
        return getProxy(state, null, null, null);
    }

    public GetProxyResponse getProxy(ProxyState state, String descr) throws IOException {
        return getProxy(state, descr, null, null);
    }

    public GetProxyResponse getProxy(ProxyState state, String descr, Integer limit) throws IOException {
        return getProxy(state, descr, null, limit);
    }

    public SetTypeResponse setType(ProxyType type, int... ids) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + SET_TYPE);

        String idsParam = Arrays.stream(ids)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?ids=").append(idsParam);
        urlBuilder.append("&type=").append(type.getCode());

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }

            return gson.fromJson(responseBody, SetTypeResponse.class);
        }
    }

    public SetDescrResponse setDescription(String newDescr, String oldDescr, int... ids) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + SET_DESCR);

        String idsParam = Arrays.stream(ids)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));

        if (oldDescr != null) {
            urlBuilder.append("?old=").append(oldDescr);
        }

        if (ids.length != 0) {
            urlBuilder.append("?ids=").append(idsParam);
        }

        urlBuilder.append("&new=").append(newDescr);

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }

            return gson.fromJson(responseBody, SetDescrResponse.class);
        }
    }

    public SetDescrResponse setDescription(String newDescr, int... ids) throws IOException {
        return setDescription(newDescr, null, ids);
    }

    public SetDescrResponse setDescription(String newDescr, String oldDescr) throws IOException {
        return setDescription(newDescr, oldDescr);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country, ProxyVersion version, ProxyType type,
            String descr, Boolean autoProlong)
            throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + BUY_PROXY);
        urlBuilder
                .append("?count=").append(count)
                .append("&period=").append(period)
                .append("&country=").append(country.getCode());

        if (version != null) {
            urlBuilder.append("&version=").append(version.getCode());
        } else {
            urlBuilder.append("&version=").append(ProxyVersion.IPV6.getCode());
        }

        if (type != null) {
            urlBuilder.append("&type=").append(type.getCode());
        } else {
            urlBuilder.append("&type=").append(ProxyType.HTTPS.getCode());
        }

        if (descr != null) {
            urlBuilder.append("&descr=").append(descr);
        }

        if (autoProlong != null && autoProlong) {
            urlBuilder.append("&auto_prolong");
        }

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }

            return gson.fromJson(responseBody, BuyResponse.class);
        }
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country)
            throws IOException {
        return buyProxy(count, period, country, null, null, null, null);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country, ProxyVersion version)
            throws IOException {
        return buyProxy(count, period, country, version, null, null, null);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country, ProxyType type)
            throws IOException {
        return buyProxy(count, period, country, null, type, null, null);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country, ProxyVersion version, ProxyType type)
            throws IOException {
        return buyProxy(count, period, country, version, type, null, false);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country, ProxyVersion version, ProxyType type,
            String descr)
            throws IOException {
        return buyProxy(count, period, country, version, type, descr, false);
    }

    public ProlongResponse prolongProxy(Integer period, int... ids) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + PROLONG_PROXY);

        String idsParam = Arrays.stream(ids)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?period=").append(period);
        urlBuilder.append("&ids=").append(idsParam);

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }

            return gson.fromJson(responseBody, ProlongResponse.class);
        }
    }

    public DeleteResponse deleteProxy(int... ids) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + DELETE);

        String idsParam = Arrays.stream(ids)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?ids=").append(idsParam);

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }
            return gson.fromJson(responseBody, DeleteResponse.class);
        }
    }

    public DeleteResponse deleteProxy(String descr) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + DELETE);

        urlBuilder.append("?descr=").append(descr);

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }
            return gson.fromJson(responseBody, DeleteResponse.class);
        }
    }

    public CheckResponse checkProxy(int... ids) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + CHECK);

        String idsParam = Arrays.stream(ids)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?ids=").append(idsParam);

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }
            return gson.fromJson(responseBody, CheckResponse.class);
        }
    }

    public IpAuthResponse ipAuth(String... ipOrDelete) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + IP_AUTH);

        String ipParam;
        if (ipOrDelete.length == 1 && "delete".equalsIgnoreCase(ipOrDelete[0])) {
            ipParam = "delete";
        } else {
            ipParam = Arrays.stream(ipOrDelete)
                    .collect(Collectors.joining(","));
        }

        urlBuilder.append("?ip=").append(ipParam);

        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();

            ErrorResponse errorResponse = gson.fromJson(responseBody, ErrorResponse.class);
            if ("no".equals(errorResponse.getStatus())) {
                ErrorCode errorCode = ErrorCode.fromCode(errorResponse.getErrorId());
                throw new ApiException(errorCode);
            }
            return gson.fromJson(responseBody, IpAuthResponse.class);
        }
    }
}
