package com.linxz.core.mvp;
import com.linxz.core.http.RestClient;

import java.util.ArrayList;
import java.util.List;
/**
 * 描述：
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  22:15
 * 版本：3.0
 * @author linxz
 */

@SuppressWarnings("unchecked")
public class BaseModel{

    public static final int SUCCESS_CODE=200;
    public static final int AUTH_FAILD=201;

    private List<RestClient> httpRestClientList=new ArrayList<>();

    public void putHttpClient(RestClient restClient){
        httpRestClientList.add(restClient);
    }

    public void onUnsubscribe() {
       for (RestClient restClient:httpRestClientList){
           if (restClient!=null){
               restClient.onUnsubscribe();
           }
       }
    }

}
