# Proxy6Api
## Proxy6.net API Java Implementation

### Способы создания клиента для работы с API:
- 1 вариант (Через статический метод)
```java
Proxy6NetApi api = Proxy6NetApi.createWithApiKey("your-api-key");
```
- 2 вариант (Конструктор)
```java
Proxy6NetApi api = new Proxy6NetApi(new OkHttpClient(), "your-api-key-here");
```
