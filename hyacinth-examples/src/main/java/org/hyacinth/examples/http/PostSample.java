package org.hyacinth.examples.http;

import java.io.IOException;  

import org.apache.commons.httpclient.*;  
import org.apache.commons.httpclient.methods.PostMethod;  
import org.apache.commons.httpclient.params.HttpMethodParams;  
  
public class PostSample{  
    public static void main(String[] args) throws Exception {  
        // 构造HttpClient的实例  
        HttpClient httpClient = new HttpClient();  
        // 创建POST方法的实例  
        String url = "http://www.oracle.com/";  
        PostMethod postMethod = new PostMethod(url);  
        // 填入各个表单域的值  
        NameValuePair[] data = { new NameValuePair("id", "youUserName"),  
        new NameValuePair("passwd", "yourPwd") };  
        // 将表单的值放入postMethod中  
        postMethod.setRequestBody(data);  
        // 执行postMethod  
        int statusCode = httpClient.executeMethod(postMethod);  
        // HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发  
        // 301或者302  
        if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY ||   
        statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {  
            // 从头中取出转向的地址  
            Header locationHeader = postMethod.getResponseHeader("location");  
            String location = null;  
            if (locationHeader != null) {  
             location = locationHeader.getValue();  
             System.out.println("The page was redirected to:" + location);  
            } else {  
             System.err.println("Location field value is null.");  
            }  
            return;  
        }  
    }  
}  
