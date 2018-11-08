package com.sse.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sse.model.RequestParamBase;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FetchServiceAddrUtil {
	
	/**
     * 获取服务的所有地址（注册在 eureka server 上的服务）
     * @param eurekaIp
     * @param eurekaPort
     * @param servicename
     * @return
     */
    public static List<String> getAllServiceAddr(String eurekaIp, String eurekaPort, String servicename) {
        List<String> result = new ArrayList<>();
        String url = "http://" + eurekaIp + ":" + eurekaPort + "/eureka/apps/" + servicename;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)//请求接口。如果需要传参拼接到接口后面。
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/xml")
                .get()
                .build();//创建Request 对象
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseContent = response.body().string();
                Matcher matcher = Pattern.compile("<homePageUrl>(.+?)</homePageUrl>").matcher(responseContent);
                while (matcher.find()) {
                    String homepage = matcher.group(1).trim();
                    if (homepage.endsWith("/")) {
						homepage = homepage.substring(0, homepage.length()-1);
					}
                    result.add(homepage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 随机获取一个微服务的地址
     * @param eurekaIp
     * @param eurekaPort
     * @param servicename
     * @return
     */
    public static String getOneServiceAddr(String eurekaIp, String eurekaPort, String servicename) {
        List<String> result = new ArrayList<>();
        String url = "http://" + eurekaIp + ":" + eurekaPort + "/eureka/apps/" + servicename;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)//请求接口。如果需要传参拼接到接口后面。
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/xml")
                .get()
                .build();//创建Request 对象
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseContent = response.body().string();
                Matcher matcher = Pattern.compile("<homePageUrl>(.+?)</homePageUrl>").matcher(responseContent);
                while (matcher.find()) {
                    String homepage = matcher.group(1).trim();
                    if (homepage.endsWith("/")) {
						homepage = homepage.substring(0, homepage.length()-1);
					}
                    result.add(homepage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.isEmpty()) {
            return "";
        } else {
            Collections.shuffle(result);
            return result.get(0);
        }
    }
    
    public static void postRequest() {
    	OkHttpClient client = new OkHttpClient();
    	RequestParamBase param = RequestParamBase.builder().id(24).name("requestParam").build();
    	ObjectMapper mapper = new ObjectMapper();
    	String json = "";
    	try {
			json = mapper.writeValueAsString(param);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    	Request request = new Request.Builder().post(body).url("http://127.0.0.1:8060/student/1").build();
    	try {
    		Response response = client.newCall(request).execute();
    		if (response.isSuccessful()) {
    			String responseString = response.body().string();
				com.sse.model.Response result = mapper.readValue(responseString, com.sse.model.Response.class);
				System.out.println(result);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    }

	public static void main(String[] args) {
		/*List<String> result = getAllServiceAddr("127.0.0.1", "8010", "SERVICE-PROVIDER");
		for(String s:result) {
			System.out.println(s);
		}*/
		
		postRequest();

	}

}
