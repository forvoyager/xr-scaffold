package com.xr.base.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * <b>author</b>: yang.changyan@foundbyte.com
 * <b>time</b>: 2021-04-08 12:19:00 <br>
 * <b>description</b>: Http访问工具类<br>
 */
public class HttpUtils {

  protected static final String DEFAULT_CHARSET = "UTF-8";

  protected static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

  protected static final String HTTP_REQUEST_LOG_PATTERN = "{} {}, with parameter {}, and cost is {}ms, http status is {}, result is {}";
  protected static final String HTTP_METHOD_POST = "POST";
  protected static final String HTTP_METHOD_GET = "GET";

  public static String post(String url) throws Exception{
    return post(url, null);
  }

  public static String post(String url, Map<String, Object> params) throws Exception{

    long startTime = System.currentTimeMillis();
    HttpURLConnection conn = getConnection(url, HTTP_METHOD_POST);

    if (params != null && params.size() > 0) {
      OutputStream outwritestream = conn.getOutputStream();
      outwritestream.write(JsonUtils.toJson(params).getBytes(DEFAULT_CHARSET));
      outwritestream.flush();
      outwritestream.close();
    }

    String result = parseResponseAsString(conn);
    conn.disconnect();

    logger.info(HTTP_REQUEST_LOG_PATTERN, HTTP_METHOD_POST, url, JsonUtils.toJson(params), (System.currentTimeMillis() - startTime), conn.getResponseCode(), result);

    return result;
  }

  public static String get(String url) throws Exception{
    return get(url, null);
  }

  public static String get(String url, Map<String, Object> params) throws Exception{

    StringBuffer finalUrl = new StringBuffer();
    if (params != null && params.size() > 0) {
      for (String key : params.keySet()) {
        finalUrl.append("&").append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), DEFAULT_CHARSET));
      }
      finalUrl.replace(0, 1, "?");
    }
    finalUrl.insert(0, url);

    long startTime = System.currentTimeMillis();
    HttpURLConnection conn = getConnection(finalUrl.toString(), HTTP_METHOD_GET);

    String result = parseResponseAsString(conn);
    conn.disconnect();

    logger.info(HTTP_REQUEST_LOG_PATTERN, HTTP_METHOD_GET, finalUrl.toString(), JsonUtils.toJson(params), (System.currentTimeMillis() - startTime), conn.getResponseCode(), result);

    return result;
  }

  public static byte[] getBytes(String url, Map<String, Object> params, String method) throws Exception{

    String finalUrl = url;
    if(HTTP_METHOD_GET.equals(method)){
      StringBuffer tmpUrl = new StringBuffer();
      if (params != null && params.size() > 0) {
        for (String key : params.keySet()) {
          tmpUrl.append("&").append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), DEFAULT_CHARSET));
        }
        tmpUrl.replace(0, 1, "?");
      }
      finalUrl = tmpUrl.insert(0, url).toString();
    }

    long startTime = System.currentTimeMillis();
    HttpURLConnection conn = getConnection(finalUrl, method);

    if(HTTP_METHOD_POST.equals(method)){
      if (params != null && params.size() > 0) {
        OutputStream outwritestream = conn.getOutputStream();
        outwritestream.write(JsonUtils.toJson(params).getBytes(DEFAULT_CHARSET));
        outwritestream.flush();
        outwritestream.close();
      }
    }

    InputStream is = conn.getInputStream();

    if(is != null){
      final ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try {
        byte buff[] = new byte[8*1024];
        int len = 0;
        while ( (len = is.read(buff) ) > 0 ) {
          baos.write(buff, 0, len);
        }
        baos.flush();
        return baos.toByteArray();
      }finally {
        logger.info(HTTP_REQUEST_LOG_PATTERN, method, url, JsonUtils.toJson(params), (System.currentTimeMillis() - startTime), conn.getResponseCode(), "");
        if(is != null){ is.close(); }
        if(baos != null){ baos.close(); }
        conn.disconnect();
      }
    }

    return new byte[]{};
  }

  private static HttpURLConnection getConnection(String url, String type) throws Exception {
    type = StringUtils.isEmpty(type) ? HTTP_METHOD_GET: type.toUpperCase();
    HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
    conn.setRequestProperty("Charset", DEFAULT_CHARSET);
    conn.setRequestProperty("accept", "*/*");
    conn.setRequestProperty("connection", "Keep-Alive");
    conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0");
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
    if(HTTP_METHOD_POST.equals(type)){
      conn.setDoOutput(true);
      conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
    }
    conn.setDoInput(true);
    conn.setUseCaches(false);
    conn.setRequestMethod(type);
    conn.setInstanceFollowRedirects(true);
    conn.setRequestProperty("accept","*/*"); // */* application/json
    conn.connect();
    return conn;
  }

  private static String parseResponseAsString(HttpURLConnection conn) throws Exception{

    InputStream responseStream = null;
    if(conn.getResponseCode() == 200) {
      responseStream = conn.getInputStream();
    }else {
      responseStream = conn.getErrorStream();
    }

    StringBuilder result = new StringBuilder();

    ByteArrayOutputStream baos = null;
    BufferedInputStream bis = null;
    try {
      int byteCount = -1;
      byte[] buffer = new byte[1024];
      baos = new ByteArrayOutputStream();
      bis = new BufferedInputStream(responseStream);
      while ( (byteCount = bis.read(buffer)) != -1 ){
        baos.write(buffer, 0, byteCount);
      }
      result.append(new String(baos.toByteArray(), DEFAULT_CHARSET));
    } finally {
      if(bis != null){ bis.close(); }
      if(baos != null){ baos.close(); }
    }

    return result.toString();
  }
}
