package com.dwalczak.newsreader.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class RestLoggingFilter extends OncePerRequestFilter {
    private static final String CHARSET = "UTF-8";
    private static final Logger apiLog = org.slf4j.LoggerFactory.getLogger("api");


    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        BufferedRequestWrapper wrappedRequest = new BufferedRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
        traceReq(wrappedRequest);
        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            traceRes(wrappedResponse);
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void traceReq(BufferedRequestWrapper request) {
        apiLog.info(formatLogParams(Arrays.asList(
                request.getMethod(),
                request.getRequestURI() + (request.getQueryString().length() > 0 ? "?" + request.getQueryString() : "")))
                + " " + request.getRequestBody())
        ;
    }

    private void traceRes(ContentCachingResponseWrapper response) throws IOException {
        String body = "";
        try {
            body = IOUtils.toString(response.getContentAsByteArray(), CHARSET);
        } catch (Exception e) {
            log.warn("Can't get response body", e);
        }
        apiLog.info(Arrays.asList(response.getStatusCode()) + " " + body);
    }

    private String formatLogParams(List<Object> params) {
        StringBuilder buf = new StringBuilder();
        params.forEach(s -> {if (s != null) buf.append('[').append(s).append(']');});
        return buf.toString();
    }
}
