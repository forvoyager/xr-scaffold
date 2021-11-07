package com.xr.base.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <b>author</b>: forvoyager@outlook.com
 * <b>description</b>: Http访问工具类<br>
 */
public class HttpUtils {

  protected static final String DEFAULT_CHARSET = "UTF-8";
  protected static final String HTTP_REQUEST_LOG_PATTERN = "{} {}, with parameter {}, and cost is {}ms, http status is {}, result is {}";
  protected static final String HTTP_METHOD_POST = "POST";
  protected static final String HTTP_METHOD_GET = "GET";
  protected static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

  public static String post(String url) throws Exception {
    return post(url, Collections.EMPTY_MAP, Collections.EMPTY_MAP);
  }

  public static String post(String url, Map<String, String> params) throws Exception {
    return post(url, params, Collections.EMPTY_MAP);
  }

  public static String post(String url, Map<String, String> params, Map<String, String> header) throws Exception {
    int responseCode = 0;
    String result = null;
    long startTime = System.currentTimeMillis();

    try {
      HttpURLConnection conn = getConnection(url, HTTP_METHOD_POST, header);
      if (params != null && params.size() > 0) {
        OutputStream outwritestream = conn.getOutputStream();
        outwritestream.write(JsonUtils.toJson(params).getBytes(DEFAULT_CHARSET));
        outwritestream.flush();
        outwritestream.close();
      }

      responseCode = conn.getResponseCode();
      result = parseResponseAsString(conn);
      conn.disconnect();
    }finally {
      logger.info(HTTP_REQUEST_LOG_PATTERN, HTTP_METHOD_POST, url, JsonUtils.toJson(params), (System.currentTimeMillis() - startTime), responseCode, result);
    }

    return result;
  }

  public static String get(String url) throws Exception {
    return get(url, Collections.EMPTY_MAP, Collections.EMPTY_MAP);
  }

  public static String get(String url, Map<String, String> params) throws Exception {
    return get(url, params, Collections.EMPTY_MAP);
  }

  public static String get(String url, Map<String, String> params, Map<String, String> header) throws Exception {

    String finalUrl = parseGetUrl(url, params);
    String result = null;
    int responseCode = 0;
    long startTime = System.currentTimeMillis();
    try {
      HttpURLConnection conn = getConnection(finalUrl, HTTP_METHOD_GET, header);
      responseCode = conn.getResponseCode();
      result = parseResponseAsString(conn);
      conn.disconnect();
    } finally {
      logger.info(HTTP_REQUEST_LOG_PATTERN, HTTP_METHOD_GET, finalUrl, JsonUtils.toJson(params), (System.currentTimeMillis() - startTime), responseCode, result);
    }

    return result;
  }

  /**
   * 获取get请求的header头信息
   * @param url
   * @param params
   * @return
   * @throws Exception
   */
  public static Map<String, List<String>> getHeader(String url, Map<String, String> params) throws Exception{

    long startTime = System.currentTimeMillis();
    String finalUrl = parseGetUrl(url, params);
    int responseCode = 0;
    try {
      HttpURLConnection conn = getConnection(finalUrl, HTTP_METHOD_GET);
      responseCode = conn.getResponseCode();
      Map<String, List<String>> header = parseResponseHeader(conn);
      conn.disconnect();
      return header;
    }finally {
      logger.info(HTTP_REQUEST_LOG_PATTERN, HTTP_METHOD_GET, finalUrl, JsonUtils.toJson(params), (System.currentTimeMillis() - startTime), responseCode, "");
    }
  }

  private static String parseGetUrl(String url, Map<String, String> params) throws Exception{
    StringBuffer finalUrl = new StringBuffer();
    if (params != null && params.size() > 0) {
      for (String key : params.keySet()) {
        finalUrl.append("&").append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), DEFAULT_CHARSET));
      }
      finalUrl.replace(0, 1, "?");
    }
    finalUrl.insert(0, url);
    return finalUrl.toString();
  }

  public static byte[] getBytes(String url, Map<String, String> params, String method) throws Exception {

    long startTime = System.currentTimeMillis();

    String finalUrl = url;
    if (HTTP_METHOD_GET.equals(method)) {
      finalUrl = parseGetUrl(url, params);
    }

    HttpURLConnection conn = getConnection(finalUrl, method);

    if (HTTP_METHOD_POST.equals(method)) {
      if (params != null && params.size() > 0) {
        OutputStream outwritestream = conn.getOutputStream();
        outwritestream.write(JsonUtils.toJson(params).getBytes(DEFAULT_CHARSET));
        outwritestream.flush();
        outwritestream.close();
      }
    }

    InputStream is = conn.getInputStream();

    if (is != null) {
      final ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try {
        byte buff[] = new byte[8 * 1024];
        int len = 0;
        while ((len = is.read(buff)) > 0) {
          baos.write(buff, 0, len);
        }
        baos.flush();
        return baos.toByteArray();
      } finally {
        logger.info(HTTP_REQUEST_LOG_PATTERN, method, url, JsonUtils.toJson(params), (System.currentTimeMillis() - startTime), conn.getResponseCode(), "");
        if (is != null) {
          is.close();
        }
        if (baos != null) {
          baos.close();
        }
        conn.disconnect();
      }
    }

    return new byte[]{};
  }

  private static HttpURLConnection getConnection(String url, String type) throws Exception {
    return getConnection(url, type, Collections.EMPTY_MAP);
  }

  private static HttpURLConnection getConnection(String url, String type, Map<String, String> header) throws Exception {
    type = StringUtils.isEmpty(type) ? HTTP_METHOD_GET : type.toUpperCase();
    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
    conn.setRequestProperty("Charset", DEFAULT_CHARSET);
    conn.setRequestProperty("accept", "*/*");
    conn.setRequestProperty("connection", "Keep-Alive");
    conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0");
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

    if (header != null && header.size() > 0) {
      for (String key : header.keySet()) {
        conn.setRequestProperty(key, header.get(key).toString());
      }
    }

    if (HTTP_METHOD_POST.equals(type)) {
      conn.setDoOutput(true);
      conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
    }
    conn.setDoInput(true);
    conn.setUseCaches(false);
    conn.setRequestMethod(type);
    conn.setInstanceFollowRedirects(true);
    conn.setRequestProperty("accept", "*/*"); // */* application/json
    conn.connect();
    return conn;
  }

  private static String parseResponseAsString(HttpURLConnection conn) throws Exception {

    InputStream responseStream = null;
    int http_status = conn.getResponseCode();
    if (http_status == 200 || http_status == 201) {
      responseStream = conn.getInputStream();
    } else {
      responseStream = conn.getErrorStream();
    }

    StringBuilder result = new StringBuilder();

    if (responseStream != null) {
      ByteArrayOutputStream baos = null;
      BufferedInputStream bis = null;
      try {
        int byteCount = -1;
        byte[] buffer = new byte[1024];
        baos = new ByteArrayOutputStream();
        bis = new BufferedInputStream(responseStream);
        while ((byteCount = bis.read(buffer)) != -1) {
          baos.write(buffer, 0, byteCount);
        }
        result.append(new String(baos.toByteArray(), DEFAULT_CHARSET));
      } finally {
        if (bis != null) {
          bis.close();
        }
        if (baos != null) {
          baos.close();
        }
      }
    }

    return result.toString();
  }

  private static Map<String, List<String>> parseResponseHeader(HttpURLConnection conn) throws Exception{

    if(conn == null){ return Collections.EMPTY_MAP; }

    return conn.getHeaderFields();
  }
}
