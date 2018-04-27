package com.linxz.core.http.old;
import com.linxz.core.app.ConfigKeys;
import com.linxz.core.app.Linxz;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述：
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  18:12
 * 版本：2.0
 * @author linxz
 */

public class ApiClient {
    public static Retrofit mRetrofit;
    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            OkHttpClient defaultHttpClient;
            if (true){
                //ok3添加拦截器,打印日志
                defaultHttpClient= new OkHttpClient.Builder()
                        .addNetworkInterceptor(new LoggingInterceptor()).build();
            }else{
                defaultHttpClient=new OkHttpClient.Builder().build();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Linxz.getConfiguration(ConfigKeys.API_HOST)+"")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(defaultHttpClient)
                    .build();
        }
        return mRetrofit;
    }
}
