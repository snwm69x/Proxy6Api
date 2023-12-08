package com.snwm.api;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.snwm.enums.Country;
import com.snwm.enums.ProxyState;
import com.snwm.enums.ProxyType;
import com.snwm.enums.ProxyVersion;
import com.snwm.objects.BuyResponse;
import com.snwm.objects.CheckResponse;
import com.snwm.objects.DeleteResponse;
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

    public GetPriceResponse getPrice(int count, int period, ProxyVersion version) {
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

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, GetPriceResponse.class);
    }

    public GetPriceResponse getPrice(int count, int period) {
        return getPrice(count, period, ProxyVersion.IPV6);
    }

    public GetCountResponse getCount(Country country, ProxyVersion version) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_COUNT);
        urlBuilder
                .append("?country=")
                .append(country.getCode());

        if (version != null) {
            urlBuilder.append("&version=")
                    .append(version.getCode());
        }

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, GetCountResponse.class);
    }

    public GetCountResponse getCount(Country country) {
        return getCount(country, ProxyVersion.IPV6);
    }

    public GetCountryResponse getCountry(ProxyVersion version) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_COUNTRY);
        urlBuilder.append("?version=")
                .append(version.getCode());

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, GetCountryResponse.class);
    }

    public GetCountryResponse getCountry() {
        return getCountry(ProxyVersion.IPV6);
    }

    public GetProxyResponse getProxy(ProxyState state, String descr, Integer page, Integer limit) {
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

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, GetProxyResponse.class);
    }

    public GetProxyResponse getProxy() {
        return getProxy(null, null, null, null);
    }

    public GetProxyResponse getProxy(ProxyState state) {
        return getProxy(state, null, null, null);
    }

    public GetProxyResponse getProxy(ProxyState state, String descr) {
        return getProxy(state, descr, null, null);
    }

    public GetProxyResponse getProxy(ProxyState state, String descr, Integer limit) {
        return getProxy(state, descr, null, limit);
    }

    public SetTypeResponse setType(ProxyType type, int... ids) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + SET_TYPE);

        String idsParam = Arrays.stream(ids)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?ids=").append(idsParam);
        urlBuilder.append("&type=").append(type.getCode());

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, SetTypeResponse.class);
    }

    public SetDescrResponse setDescription(String newDescr, String oldDescr, int... ids) {
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

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, SetDescrResponse.class);
    }

    public SetDescrResponse setDescription(String newDescr, int... ids) {
        return setDescription(newDescr, null, ids);
    }

    public SetDescrResponse setDescription(String newDescr, String oldDescr) {
        return setDescription(newDescr, oldDescr);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country, ProxyVersion version, ProxyType type,
            String descr, Boolean autoProlong) {
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

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, BuyResponse.class);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country) {
        return buyProxy(count, period, country, null, null, null, null);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country, ProxyVersion version) {
        return buyProxy(count, period, country, version, null, null, null);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country, ProxyType type) {
        return buyProxy(count, period, country, null, type, null, null);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country, ProxyVersion version, ProxyType type) {
        return buyProxy(count, period, country, version, type, null, false);
    }

    public BuyResponse buyProxy(Integer count, Integer period, Country country, ProxyVersion version, ProxyType type,
            String descr) {
        return buyProxy(count, period, country, version, type, descr, false);
    }

    public ProlongResponse prolongProxy(Integer period, int... ids) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + PROLONG_PROXY);

        String idsParam = Arrays.stream(ids)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?period=").append(period);
        urlBuilder.append("&ids=").append(idsParam);

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, ProlongResponse.class);
    }

    public DeleteResponse deleteProxy(int... ids) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + DELETE);

        String idsParam = Arrays.stream(ids)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?ids=").append(idsParam);

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, DeleteResponse.class);
    }

    public DeleteResponse deleteProxy(String descr) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + DELETE);

        urlBuilder.append("?descr=").append(descr);

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, DeleteResponse.class);
    }

    public CheckResponse checkProxy(int... ids) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + CHECK);

        String idsParam = Arrays.stream(ids)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(","));

        urlBuilder.append("?ids=").append(idsParam);

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, CheckResponse.class);
    }

    public IpAuthResponse ipAuth(String... ipOrDelete) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + IP_AUTH);

        String ipParam;
        if (ipOrDelete.length == 1 && "delete".equalsIgnoreCase(ipOrDelete[0])) {
            ipParam = "delete";
        } else {
            ipParam = Arrays.stream(ipOrDelete)
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
