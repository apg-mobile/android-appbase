package com.apg.mobile.appbase.restful;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by sattha on 2/9/2559.
 */

public class RetrofitClient {

    private String baseUrl;
    private List<Converter.Factory> converters = new ArrayList<>();
    private ArrayList<CallAdapter.Factory> adapters = new ArrayList<>();
    private Map<String, String> headers = new HashMap<>();
    private HttpLoggingInterceptor.Level logLevel;
    private long connectionTimeout;
    private long readTimeout;
    private TimeUnit connectionTimeoutUnit;
    private TimeUnit readTimeoutUnit;

    private RetrofitClient() {

    }

    public static class Builder {

        private String baseUrl;
        private List<Converter.Factory> converters = new ArrayList<>();
        private ArrayList<CallAdapter.Factory> adapters = new ArrayList<>();
        private Map<String, String> headers = new HashMap<>();
        private HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.BODY;
        private long connectionTimeout = 30 * 1000;
        private long readTimeout = 30 * 1000;
        private TimeUnit connectionTimeUnit = TimeUnit.MILLISECONDS;
        private TimeUnit readTimeoutUnit = TimeUnit.MILLISECONDS;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder addHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder addCallAdapterFactory(CallAdapter.Factory adapter) {
            this.adapters.add(adapter);
            return this;
        }

        public Builder addConverterFactory(Converter.Factory converter) {
            this.converters.add(converter);
            return this;
        }

        public Builder setLogLevel(HttpLoggingInterceptor.Level level) {
            this.logLevel = level;
            return this;
        }

        public Builder setConnectionTimeout(long timeout, TimeUnit timeUnit) {
            this.connectionTimeout = timeout;
            this.connectionTimeUnit = timeUnit;
            return this;
        }

        public Builder setReadTimeout(long timeout, TimeUnit timeUnit) {
            this.readTimeout = timeout;
            this.readTimeoutUnit = timeUnit;
            return this;
        }

        public <T> T build(Class<T> serviceClass) {
            RetrofitClient client = new RetrofitClient();
            client.baseUrl = baseUrl;
            client.adapters = adapters;
            client.converters = converters;
            client.logLevel = logLevel;
            client.headers = headers;
            client.connectionTimeout = connectionTimeout;
            client.connectionTimeoutUnit = connectionTimeUnit;
            client.readTimeout = readTimeout;
            client.readTimeoutUnit = readTimeoutUnit;

            return new RetrofitClient().init().create(serviceClass);
        }

        public Retrofit create() {
            RetrofitClient client = new RetrofitClient();
            client.baseUrl = baseUrl;
            client.adapters = adapters;
            client.converters = converters;
            client.logLevel = logLevel;
            client.headers = headers;
            client.connectionTimeout = connectionTimeout;
            client.connectionTimeoutUnit = connectionTimeUnit;
            client.readTimeout = readTimeout;
            client.readTimeoutUnit = readTimeoutUnit;

            return new RetrofitClient().init();
        }
    }

    private Retrofit init() {

        // set log level
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(logLevel);

        // set header
        final Interceptor header = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
                return chain.proceed(builder.build());
            }
        };

        // set http client instance
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(connectionTimeout, connectionTimeoutUnit)
                .readTimeout(readTimeout, readTimeoutUnit)
                .addInterceptor(header)
                .build();

        // set retrofit instance
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient);

        for (Converter.Factory converter : converters) {
            retrofitBuilder.addConverterFactory(converter);
        }

        for (CallAdapter.Factory converter : adapters) {
            retrofitBuilder.addCallAdapterFactory(converter);
        }

        return retrofitBuilder.build();
    }
}
