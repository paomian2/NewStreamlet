package com.linxz.core.http.interceptors;

import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.linxz.core.utils.file.FileUtil;
import com.linxz.core.utils.log.LinxzLogger;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * @author linxz
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return logger(chain);
    }

    private Response logger(Chain chain){
        Request originalRequest = chain.request();
        try {
            //请求url
            String requestURL = originalRequest.url().toString();
            Request request = null;
            LinxzLogger.d(requestURL);
            String method = originalRequest.method();
            final String POST="POST";
            if (POST.equals(method) && !requestURL.contains("/file/images")) {
                StringBuilder sb = new StringBuilder();
                if (originalRequest.body() instanceof FormBody) {
                    FormBody body = (FormBody) originalRequest.body();
                    for (int i = 0; i < body.size(); i++) {
                        sb.append(body.encodedName(i)).append("=").append(body.encodedValue(i)).append(",");
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    LinxzLogger.d("| RequestParams:{" + sb.toString() + "}");
                } else {
                    Buffer buffer = new Buffer();
                    originalRequest.body().writeTo(buffer);
                    String oldParamsJson = buffer.readUtf8();
                    request = originalRequest;
                    LinxzLogger.d("| RequestParams:{" + oldParamsJson+ "}");
                }
            } else {
                request = originalRequest;
            }
            //释放拦截，进行网络访问
            okhttp3.Response response = chain.proceed(request);
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LinxzLogger.d(content);
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
