package cn.happycoding.www.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/**
 * Copyright © 2017优质的代码创造美好的生活. All rights reserved.
 * @description http访问工具
 * @version V1.0.0
 * @motto
 * 人生三大境界：
 * 昨夜西风凋碧树，独上高楼，望尽天涯路
 * 衣带渐宽终不悔，为伊消得人憔悴
 * 众里寻他千百度。蓦然回首，那人却在灯火阑珊处
 * @className HttpTools
 * @author xujie(dear_xujie@foxmail.com)
 * @date 2017/9/18
 */
public class HttpTools {


    /**
     * @description http请求（GET请求）
     * @version V1.0.0
     * @motto 我坚信，好的程序从好的注释开始.....
     * @methodName sendGet
     * @author xujie(dear_xujie@foxmail.com)
     * @date 2017/5/25
     * @param reqUrl 请求连接
     * @param params 请求参数集合
     * @return
     */
    @SuppressWarnings("resource")
    public static String sendGet(String reqUrl, Map<String, String> params)
            throws Exception {
        InputStream inputStream = null;
        HttpGet request = new HttpGet();
        try {
            String url = buildUrl(reqUrl, params);
            System.out.println(url);
            HttpClient client = new DefaultHttpClient();
            request.setHeader("Accept-Encoding", "gzip");
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            inputStream = response.getEntity().getContent();
            String result = getJsonStringFromGZIP(inputStream);
            return result;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            request.releaseConnection();
        }

    }

    /**
     * @description http请求（POST请求）
     * @version V1.0.0
     * @motto 我坚信，好的程序从好的注释开始.....
     * @methodName sendPost
     * @author xujie(dear_xujie@foxmail.com)
     * @date 2017/5/25
     * @param reqUrl 请求连接
     * @param params 请求参数集合
     * @return
     */
    @SuppressWarnings("resource")
    public static String sendPost(String reqUrl, Map<String, String> params)
            throws Exception {
        try {
            Set<String> set = params.keySet();
            List<NameValuePair> list = new ArrayList<>();
            for (String key : set) {
                list.add(new BasicNameValuePair(key, params.get(key)));
            }
            if (list.size() > 0) {
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpPost request = new HttpPost(reqUrl);

                    request.setHeader("Accept-Encoding", "gzip");
                    request.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));

                    HttpResponse response = client.execute(request);

                    InputStream inputStream = response.getEntity().getContent();
                    try {
                        String result = getJsonStringFromGZIP(inputStream);

                        return result;
                    } finally {
                        inputStream.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new Exception("网络连接失败,请连接网络后再试");
                }
            } else {
                throw new Exception("参数不全，请稍后重试");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("发送未知异常");
        }
    }

    /**
     * @description  POST请求json数据
     * @version V1.0.0
     * @motto 我坚信，好的程序从好的注释开始.....
     * @methodName sendPostBuffer
     * @author xujie(dear_xujie@foxmail.com)
     * @date 2017/5/25
     * @param urls 请求连接
     * @param params 请求参数
     * @return
     */
    public static String sendPostBuffer(String urls, String params) {
        String retSrc = null;
        try {
            HttpPost request = new HttpPost(urls);
            StringEntity se = new StringEntity(params, HTTP.UTF_8);
            request.setEntity(se);
            // 发送请求
            @SuppressWarnings("resource")
            HttpResponse httpResponse = null;
            httpResponse = new DefaultHttpClient().execute(request);
            // 得到应答的字符串，这也是一个 JSON 格式保存的数据
            retSrc = EntityUtils.toString(httpResponse.getEntity());
            request.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retSrc;

    }

    /**
     * @description  POST请求xml数据
     * @version V1.0.0
     * @motto 我坚信，好的程序从好的注释开始.....
     * @methodName sendPostBuffer
     * @author xujie(dear_xujie@foxmail.com)
     * @date 2017/5/25
     * @param urlStr 请求连接
     * @param xmlInfo 请求参数
     * @return
     */
    public static String sendXmlPost(String urlStr, String xmlInfo) {
        // xmlInfo xml 具体字符串
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            OutputStreamWriter out = new OutputStreamWriter(
                    con.getOutputStream());
            out.write(new String(xmlInfo.getBytes("utf-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String lines = "";
            for (String line = br.readLine(); line != null; line = br
                    .readLine()) {
                lines = lines + line;
            }
            return lines; // 返回请求结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    /**
     * @description 转换微信返回的数据为json数据
     * @version V1.0.0
     * @motto 我坚信，好的程序从好的注释开始.....
     * @methodName getJsonStringFromGZIP
     * @author xujie(dear_xujie@foxmail.com)
     * @date 2017/5/25
     * @param is
     * @return String 字符串
     */
    private static String getJsonStringFromGZIP(InputStream is) {
        String jsonString = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            bis.mark(2);
            // 取前两个字节
            byte[] header = new byte[2];
            int result = bis.read(header);
            // reset 输入流到开始位置
            bis.reset();
            // 判断是否是 GZIP 格式
            int headerData = getShort(header);
            // Gzip 流 的前两个字节是 0x1f8b
            if (result != -1 && headerData == 0x1f8b) {
                // LogUtil.i("HttpTask", " use GZIPInputStream  ");
                is = new GZIPInputStream(bis);
            } else {
                // LogUtil.d("HttpTask", " not use GZIPInputStream");
                is = bis;
            }
            InputStreamReader reader = new InputStreamReader(is, "utf-8");
            char[] data = new char[100];
            int readSize;
            StringBuffer sb = new StringBuffer();
            while ((readSize = reader.read(data)) > 0) {
                sb.append(data, 0, readSize);
            }
            jsonString = sb.toString();
            bis.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    private static int getShort(byte[] data) {
        return (data[0] << 8) | data[1] & 0xFF;
    }

    /**
     * 构建 get 方式的 url
     * @param reqUrl
     *            基础的 url 地址
     * @param params
     *            查询参数
     * @return 构建好的 url
     */
    public static String buildUrl(String reqUrl, Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        Set<String> set = params.keySet();
        for (String key : set) {
            query.append(String.format("%s=%s&", key, params.get(key)));
        }
        return reqUrl + "?" + query.toString();
    }


    /**
     * post方法，传递参数为字符串
     * @param url
     * @param body
     * @return
     */
    public static String doPost(String url, String body){
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(body, "UTF-8");
            httpPost.setEntity(entity);
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httpPost);
            String resultContent = new Utf8ResponseHandler()
                    .handleResponse(response);
            System.out.println("result=" + resultContent);
            return resultContent;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * utf-8编码
     */
    private static class Utf8ResponseHandler implements ResponseHandler<String> {
        public String handleResponse(final HttpResponse response)
                throws HttpResponseException, IOException {
            final StatusLine statusLine = response.getStatusLine();
            final HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                EntityUtils.consume(entity);
                throw new HttpResponseException(statusLine.getStatusCode(),
                        statusLine.getReasonPhrase());
            }
            return entity == null ? null : EntityUtils
                    .toString(entity, "UTF-8");
        }

    }
}
