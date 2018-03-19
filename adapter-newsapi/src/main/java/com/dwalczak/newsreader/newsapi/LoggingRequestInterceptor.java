package com.dwalczak.newsreader.newsapi;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final String CHARSET = "UTF-8";
    private static final Logger newsapiLog = org.slf4j.LoggerFactory.getLogger("newsapi");

    @Override public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceReq(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceRes(response);
        return response;
    }
    private void traceReq(HttpRequest request, byte[] body) throws IOException {
        newsapiLog.info(formatLogParams(Arrays.asList(request.getMethod(), request.getURI())) + " " + new String(body, CHARSET));
    }

    private void traceRes(ClientHttpResponse response) throws IOException {
        String body = "";
        try {
            body = IOUtils.toString(response.getBody(), CHARSET);
        } catch (Exception e) {
            log.error("Can't read message body", e);
        }
        newsapiLog.info(formatLogParams(Arrays.asList(response.getStatusCode())) + " " + body);
    }

    private String formatLogParams(List<Object> params) {
        StringBuilder buf = new StringBuilder();
        params.forEach(s -> buf.append('[').append(s).append(']'));
        return buf.toString();
    }
}
