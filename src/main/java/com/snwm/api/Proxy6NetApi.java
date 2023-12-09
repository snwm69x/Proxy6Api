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

    /**
     * Получает цену прокси.
     *
     * @param r Запрос, содержащий параметры для получения цены. Параметры включают:
     *          - count: Количество прокси, для которых нужно получить цену. Должно
     *          быть больше 0.
     *          - period: Период времени, на который нужно получить цену. Должен
     *          быть больше 0.
     *          - version: Версия прокси (IPv4 или IPv6). Если не указана, по
     *          умолчанию используется IPv6.
     * @return Ответ с информацией о цене. Ответ включает:
     *         - price: Общая цена за указанное количество прокси на указанный
     *         период.
     *         - price_single: Цена за один прокси на указанный период.
     *         - period: Период времени, на который была получена цена.
     *         - count: Количество прокси, для которых была получена цена.
     */

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

    /**
     * Получает количество доступных прокси для указанной страны.
     *
     * @param r Запрос, содержащий параметры для получения количества прокси.
     *          Параметры включают:
     *          - country: Страна, для которой нужно получить количество доступных
     *          прокси.
     *          - version: Версия прокси (IPv4 или IPv6). Если не указана, по
     *          умолчанию используется IPv6.
     * @return Ответ с информацией о количестве прокси. Ответ включает:
     *         - count: Количество доступных прокси для указанной страны.
     */

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

    /**
     * Получает информацию о доступных странах для прокси.
     *
     * @param r Запрос, содержащий параметры для получения информации о странах.
     *          Параметры включают:
     *          - version: Версия прокси (IPv4 или IPv6). Если не указана, по
     *          умолчанию используется IPv6.
     * @return Ответ с информацией о доступных странах. Ответ включает:
     *         - list: Список доступных стран в формате кодов стран.
     *         - getCountriesAsEnumList: Метод для получения списка стран в виде
     *         списка перечислений.
     */

    public GetCountryResponse getCountry(GetCountryRequest r) {
        StringBuilder urlBuilder = new StringBuilder(API_URL + apiKey + GET_COUNTRY);
        urlBuilder.append("?version=")
                .append(r.getVersion().getCode());

        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .build();

        return requestHandler.execute(request, GetCountryResponse.class);
    }

    /**
     * Получает прокси.
     *
     * @param r Запрос, содержащий параметры для получения прокси. Параметры
     *          включают:
     *          - state: Состояние прокси (все, активные, неактивные). Если не
     *          указано, по умолчанию используются все.
     *          - descr: Описание прокси.
     *          - page: Номер страницы с прокси. Если не указан, по умолчанию
     *          используется первая страница.
     *          - limit: Количество прокси на странице. Если не указано, по
     *          умолчанию используется 1000.
     * @return Ответ с информацией о прокси. Ответ включает:
     *         - list_count: Количество прокси в списке.
     *         - list: Список информации о прокси.
     */

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

    /**
     * Устанавливает тип прокси.
     *
     * @param r Запрос, содержащий параметры для установки типа прокси. Параметры
     *          включают:
     *          - type: Тип прокси, который нужно установить. Не может быть null.
     *          - ids: Список идентификаторов прокси, для которых нужно установить
     *          тип. Не может быть null или пустым.
     * @return Ответ с результатом установки типа прокси. Ответ включает:
     *         - status: Статус выполнения запроса.
     *         - user_id: Идентификатор пользователя.
     *         - balance: Баланс пользователя.
     *         - currency: Валюта баланса.
     */

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

    /**
     * Устанавливает описание прокси.
     *
     * @param r Запрос, содержащий параметры для установки описания прокси.
     *          Параметры включают:
     *          - newDescr: Новое описание прокси. Не может быть null и не может
     *          быть длиннее 50 символов.
     *          - oldDescr: Старое описание прокси. Если указано, все прокси с этим
     *          описанием получат новое описание.
     *          - ids: Список идентификаторов прокси, для которых нужно установить
     *          описание. Если указано, только эти прокси получат новое описание.
     *          Примечание: Можно установить либо oldDescr, либо ids, но не оба
     *          параметра одновременно.
     * @return Ответ с результатом установки описания прокси. Ответ включает:
     *         - count: Количество прокси, для которых было установлено описание.
     */

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

    /**
     * Покупает прокси.
     *
     * @param r Запрос, содержащий параметры для покупки прокси. Параметры включают:
     *          - count: Количество прокси для покупки. Должно быть больше 0.
     *          - period: Период покупки прокси. Должен быть больше 0.
     *          - country: Страна прокси. Не может быть null.
     *          - version: Версия прокси (IPv4 или IPv6). Если не указана, по
     *          умолчанию используется IPv6.
     *          - type: Тип прокси (HTTP или HTTPS). Если не указан, по умолчанию
     *          используется HTTPS.
     *          - descr: Описание прокси.
     *          - autoProlong: Автоматическое продление прокси. Если не указано, по
     *          умолчанию не используется.
     * @return Ответ с информацией о купленном прокси. Ответ включает:
     *         - count: Количество купленных прокси.
     *         - price: Цена покупки.
     *         - period: Период покупки.
     *         - country: Страна прокси.
     *         - list: Список информации о купленных прокси.
     */

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

    /**
     * Продлевает срок действия прокси.
     *
     * @param r Запрос, содержащий параметры для продления прокси. Параметры
     *          включают:
     *          - period: Период продления прокси. Должен быть больше 0.
     *          - ids: Список идентификаторов прокси, которые нужно продлить. Не
     *          может быть null или пустым.
     * @return Ответ с результатом продления прокси. Ответ включает:
     *         - price: Цена продления.
     *         - period: Период продления.
     *         - count: Количество продленных прокси.
     *         - list: Список информации о продленных прокси.
     */

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

    /**
     * Удаляет прокси.
     *
     * @param r Запрос, содержащий параметры для удаления прокси. Параметры
     *          включают:
     *          - descr: Описание прокси, которые нужно удалить. Если указано, все
     *          прокси с этим описанием будут удалены.
     *          - ids: Список идентификаторов прокси, которые нужно удалить. Если
     *          указано, только эти прокси будут удалены.
     *          Примечание: Можно установить либо descr, либо ids, но не оба
     *          параметра одновременно.
     * @return Ответ с результатом удаления прокси. Ответ включает:
     *         - count: Количество удаленных прокси.
     */

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

    /**
     * Проверяет прокси.
     *
     * @param r Запрос, содержащий параметры для проверки прокси. Параметры
     *          включают:
     *          - ids: Список идентификаторов прокси, которые нужно проверить. Не
     *          может быть null или пустым.
     * @return Ответ с результатом проверки прокси. Ответ включает:
     *         - proxy_id: Идентификатор проверенного прокси.
     *         - proxy_status: Статус проверенного прокси. True, если прокси
     *         активен, и false, если нет.
     */

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

    /**
     * Авторизует IP-адрес.
     *
     * @param r Запрос, содержащий параметры для авторизации IP-адреса. Параметры
     *          включают:
     *          - ipOrDelete: Список IP-адресов для авторизации или команда "delete"
     *          для удаления всех авторизованных IP-адресов.
     * @return Ответ с результатом авторизации IP-адреса. Ответ включает:
     *         - status: Статус операции.
     *         - user_id: Идентификатор пользователя.
     *         - balance: Баланс пользователя.
     *         - currency: Валюта баланса.
     */

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

    /**
     * Создает новый экземпляр API с указанным ключом API.
     *
     * @param apiKey Ключ API.
     * @return Новый экземпляр API.
     */

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
