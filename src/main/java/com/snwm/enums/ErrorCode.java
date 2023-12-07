package com.snwm.enums;

public enum ErrorCode {
    ERROR_UNKNOWN(30, "Неизвестная ошибка"),
    ERROR_KEY(100, "Ошибка авторизации, неверный ключ"),
    ERROR_IP(105,
            "Доступ к API произошел с неверного IP (если включено ограничение), либо некорректный формат IP адреса"),
    ERROR_METHOD(110, "Ошибочный метод"),
    ERROR_COUNT(200, "Ошибка кол-ва прокси, неверно указано кол-во, либо отсутствует"),
    ERROR_PERIOD(210, "Ошибка периода, неверно указано кол-во (дней), либо отсутствует"),
    ERROR_COUNTRY(220, "Ошибка страны, неверно указана страна (страны указываются в формате iso2), либо отсутствует"),
    ERROR_IDS(230, "Ошибка списка номеров прокси. Номера прокси должны быть указаны через запятую"),
    ERROR_VERSION(240, "Некорректно указана версия прокси"),
    ERROR_DESCR(250, "Ошибка технического комментария, неверно указан, либо отсутствует"),
    ERROR_TYPE(260, "Ошибка типа (протокола) прокси, неверно указан, либо отсутствует"),
    ERROR_ACTIVE_PROXY_ALLOW(300,
            "Ошибка кол-ва прокси. Возникает при попытке покупки большего кол-ва прокси, чем доступно на сервисе"),
    ERROR_NO_MONEY(400,
            "Ошибка баланса. На вашем балансе отсутствуют средства, либо их не хватает для покупки запрашиваемого кол-ва прокси"),
    ERROR_NOT_FOUND(404, "Ошибка поиска. Возникает когда запрашиваемый элемент не найден"),
    ERROR_PRICE(410, "Ошибка расчета стоимости. Итоговая стоимость меньше, либо равна нулю"),
    ERROR_REQUEST_LIMIT(503, "Превышен лимит запросов");

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ErrorCode fromCode(int code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.code == code) {
                return errorCode;
            }
        }
        return ERROR_UNKNOWN;
    }
}
