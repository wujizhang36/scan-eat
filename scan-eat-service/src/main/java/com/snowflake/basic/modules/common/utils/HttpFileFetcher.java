package com.snowflake.basic.modules.common.utils;


import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.HttpEntity;

import java.io.InputStream;

public class HttpFileFetcher {
    private static final PoolingHttpClientConnectionManager connManager;
    private static final CloseableHttpClient client;

    static {
        connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(100); // 连接池最大连接数
        connManager.setDefaultMaxPerRoute(10); // 每个路由最大连接数

        client = HttpClients.custom()
                .setConnectionManager(connManager)
                .build();
    }

    /**
     * 获取文件大小和输入流
     */
    public static FileStreamData fetchFile(String urlPath) throws Exception {
        HttpGet request = new HttpGet(urlPath);
        var response = client.execute(request);
        HttpEntity entity = response.getEntity();
        long size = entity.getContentLength();
        InputStream stream = entity.getContent(); // 需要手动关闭
        return new FileStreamData(size, stream, response); // 让调用方关闭
    }

    /**
     * 关闭 HttpClient 连接池
     */
    public static void closeClient() {
        try {
            client.close();
            connManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件数据封装类
     */
    public static class FileStreamData {
        public final long size;
        public final InputStream stream;
        public final CloseableHttpResponse response;

        public FileStreamData(long size, InputStream stream, CloseableHttpResponse response) {
            this.size = size;
            this.stream = stream;
            this.response = response;
        }

        public void close() {
            try {
                stream.close();
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
