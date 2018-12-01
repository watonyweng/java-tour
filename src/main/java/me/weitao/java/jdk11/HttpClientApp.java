package me.weitao.java.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

/**
 * Http客户端使用
 *
 * @author Watony Weng
 * @date 2018/11/30
 */

public class HttpClientApp {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientApp.class);

    /**
     * 主方法
     * @param args 启动参数
     * @throws IOException IO异常
     * @throws InterruptedException 中断异常
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://javastack.cn"))
                .build();
        var client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (logger.isDebugEnabled()) {
            logger.debug(MessageFormat.format("Response Content : {0}", response.body()));
        }
    }
}
