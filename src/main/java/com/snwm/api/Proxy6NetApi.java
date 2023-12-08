package com.snwm.api;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.snwm.objects.response.GetCountResponse;
import com.snwm.objects.request.BuyProxyRequest;
import com.snwm.objects.request.CheckProxyRequest;
import com.snwm.objects.request.DeleteProxyRequest;
import com.snwm.objects.request.GetCountRequest;
import com.snwm.objects.request.GetCountryRequest;
import com.snwm.objects.request.GetPriceRequest;
import com.snwm.objects.request.GetProxyRequest;
import com.snwm.objects.request.IpAuthRequest;
import com.snwm.objects.request.ProlongRequest;
import com.snwm.objects.request.SetDescrRequest;
import com.snwm.objects.request.SetTypeRequest;
import com.snwm.objects.response.BuyProxyResponse;
import com.snwm.objects.response.CheckProxyResponse;
import com.snwm.objects.response.DeleteProxyResponse;
import com.snwm.objects.response.GetCountryResponse;
import com.snwm.objects.response.GetPriceResponse;
import com.snwm.objects.response.GetProxyResponse;
import com.snwm.objects.response.IpAuthResponse;
import com.snwm.objects.response.ProlongResponse;
import com.snwm.objects.response.SetDescrResponse;
import com.snwm.objects.response.SetTypeResponse;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
    private final ApiRequestHandler requestHandler;

    public GetPriceResponse getPrice(GetPriceRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_PRICE);
        urlBuilder
                .append("?count=")
                .append(r.getCount())
                .append("&period=")
                .append(r.getPeriod());

        if (r.getVersion() != null) {
            urlBuilder.append("&version=")
                    .append(r.getVersion().getCode());
        }

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, GetPriceResponse.class);
    }

    public GetCountResponse getCount(GetCountRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_COUNT);
        urlBuilder
                .append("?country=")
                .append(r.getCountry().getCode());

        if (r.getVersion() != null) {
            urlBuilder.append("&version=")
                    .append(r.getVersion().getCode());
        }

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, GetCountResponse.class);
    }

    public GetCountryResponse getCountry(GetCountryRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_COUNTRY);
        urlBuilder.append("?version=")
                .append(r.getVersion().getCode());

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, GetCountryResponse.class);
    }

    public GetProxyResponse getProxy(GetProxyRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_PROXY);

        if (r.getState() != null) {
            urlBuilder.append("?state=").append(r.getState().getCode());
        }

        if (r.getDescr() != null) {
            urlBuilder.append("&descr=").append(r.getDescr());
        }

        if (Integer.valueOf(r.getPage()) != null && r.getPage() > 0) {
            urlBuilder.append("&page=").append(r.getPage());
        }

        if (Integer.valueOf(r.getLimit()) != null && r.getLimit() > 0) {
            urlBuilder.append("&limit=").append(r.getLimit());
        }

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, GetProxyResponse.class);
    }

    public SetTypeResponse setType(SetTypeRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + SET_TYPE);

        String idsParam = r.getIds().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?ids=").append(idsParam);
        urlBuilder.append("&type=").append(r.getType().getCode());

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, SetTypeResponse.class);
    }

    public SetDescrResponse setDescription(SetDescrRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + SET_DESCR);

        String idsParam = r.getIds().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        if (r.getOldDescr() != null) {
            urlBuilder.append("?old=").append(r.getOldDescr());
        }

        if (r.getIds().size() != 0) {
            urlBuilder.append("?ids=").append(idsParam);
        }

        urlBuilder.append("&new=").append(r.getNewDescr());

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, SetDescrResponse.class);
    }

    public BuyProxyResponse buyProxy(BuyProxyRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + BUY_PROXY);
        urlBuilder
                .append("?count=").append(r.getCount())
                .append("&period=").append(r.getPeriod())
                .append("&country=").append(r.getCountry().getCode());

        if (r.getVersion() != null) {
            urlBuilder.append("&version=").append(r.getVersion().getCode());
        }

        if (r.getType() != null) {
            urlBuilder.append("&type=").append(r.getType().getCode());
        }

        if (r.getDescr() != null) {
            urlBuilder.append("&descr=").append(r.getDescr());
        }

        if (r.getAutoProlong() != null && r.getAutoProlong()) {
            urlBuilder.append("&auto_prolong");
        }

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, BuyProxyResponse.class);
    }

    public ProlongResponse prolongProxy(ProlongRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + PROLONG_PROXY);

        String idsParam = r.getIds().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?period=").append(r.getPeriod());
        urlBuilder.append("&ids=").append(idsParam);

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, ProlongResponse.class);
    }

    public DeleteProxyResponse deleteProxy(DeleteProxyRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + DELETE);

        if (r.getIds() != null && !r.getIds().isEmpty()) {
            String idsParam = r.getIds().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            urlBuilder.append("?ids=").append(idsParam);
        } else if (r.getDescr() != null) {
            urlBuilder.append("?descr=").append(r.getDescr());
        }

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, DeleteProxyResponse.class);
    }

    public CheckProxyResponse checkProxy(CheckProxyRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + CHECK);

        String idsParam = r.getIds().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?ids=").append(idsParam);

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, CheckProxyResponse.class);
    }

    public IpAuthResponse ipAuth(IpAuthRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + IP_AUTH);

        String ipParam;
        if (r.getIpOrDelete().size() == 1 && "delete".equalsIgnoreCase(r.getIpOrDelete().get(0))) {
            ipParam = "delete";
        } else {
            ipParam = r.getIpOrDelete().stream()
                    .collect(Collectors.joining(","));
        }

        urlBuilder.append("?ip=").append(ipParam);

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, IpAuthResponse.class);
    }

    public static Proxy6NetApi createWithApiKey(String apiKey) {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        Gson gson = new Gson();
        ApiRequestHandler requestHandler = new ApiRequestHandler(client, gson);

        return new Proxy6NetApi(apiKey, requestHandler);
    }
}
