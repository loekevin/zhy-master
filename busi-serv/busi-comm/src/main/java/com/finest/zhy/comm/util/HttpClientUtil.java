package com.finest.zhy.comm.util;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by kezy on 2018/8/23.
 */
public class HttpClientUtil {

    /**
     * 对象通过反射机制获取key,value
     *     转换成map
     * **/
    public static Map<String, String> getKeyAndValue(Object obj) {
        Map<String, String> map = new HashMap<String, String>();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
//            Object val = new Object();
            try {
                Class type = f.getType();
                String name = type.getName();
                if(name.indexOf("Date")>-1){
                    Date time = (Date)f.get(obj);
                    String dateTime = FormatUtils.formatDate(time,"yyyy-MM-dd HH:mm:ss");
                    // 得到此属性的值
                    map.put(f.getName(), dateTime);// 设置键值
                }
//                else if(name.indexOf("Double")>-1){
//                    Double val = f.get(obj);
//                    map.put(f.getName(), String.valueOf(val));// 设置键值
//                }else if(name.indexOf("int")>-1){
//                    int val = f.getInt(obj);
//                    map.put(f.getName(), String.valueOf(val));// 设置键值
//                }else if(name.indexOf("long")>-1){
//                    long val = f.getLong(obj);
//                    map.put(f.getName(), String.valueOf(val));// 设置键值
//                }
                else{
                    Object val = f.get(obj);
                    map.put(f.getName(), val==null?"":val+"");// 设置键值
                    System.out.println("单个对象的所有键值"+f.getName()+"==反射==" + val);
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("单个对象的所有键值==反射==" + map.toString());
        return map;
    }

    /**
     * post请求
     * @param url
     * @return
     */
    public static String doPostMap(String url, Map<String, String> map, String encoding) throws ParseException, IOException {
        String body = "";

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (map != null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

        System.out.println("请求地址："+ url);
        System.out.println("请求参数："+ nvps.toString());

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        int state = response.getStatusLine().getStatusCode();
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        if("".equals(body)){
            body = "{\"status\":"+state+"}";
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }


    /**
     * post请求（用于请求json格式的参数）
     * @param url
     * @return
     */
    public static String doPostJson(String url, String key, String value) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        //设置参数到请求对象中
        nvps.add(new BasicNameValuePair(key, value));
//        StringEntity entity = new StringEntity(value, charSet);
//        httpPost.setEntity(entity);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, charSet));
        System.out.println("请求地址："+ url);
        System.out.println("请求参数："+ nvps.toString());
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
                System.out.println("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * get请求
     * @param httpurl
     * @return
     */
    public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }else{
                System.out.println("请求返回:"+connection.getResponseCode()+"("+result+")");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }


}
