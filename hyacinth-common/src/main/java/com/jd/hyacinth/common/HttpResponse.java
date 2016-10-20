/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package com.jd.hyacinth.common;

import java.util.Vector;  
   
/**
 * HTTP回应对象
 * @author J-ONE
 * @since 2016-10-20
 */  
public class HttpResponse {  
   
    String urlString;  
   
    int defaultPort;  
   
    String file;  
   
    String host;  
   
    String path;  
   
    int port;  
   
    String protocol;  
   
    String query;  
   
    String ref;  
   
    String userInfo;  
   
    String contentEncoding;  
   
    String content;  
   
    String contentType;  
   
    int code;  
   
    String message;  
   
    String method;  
   
    int connectTimeout;  
   
    int readTimeout;  
   
    Vector<String> contentCollection;  
   
    public String getContent() {  
        return content;  
    }  
   
    public String getContentType() {  
        return contentType;  
    }  
   
    public int getCode() {  
        return code;  
    }  
   
    public String getMessage() {  
        return message;  
    }  
   
    public Vector<String> getContentCollection() {  
        return contentCollection;  
    }  
   
    public String getContentEncoding() {  
        return contentEncoding;  
    }  
   
    public String getMethod() {  
        return method;  
    }  
   
    public int getConnectTimeout() {  
        return connectTimeout;  
    }  
   
    public int getReadTimeout() {  
        return readTimeout;  
    }  
   
    public String getUrlString() {  
        return urlString;  
    }  
   
    public int getDefaultPort() {  
        return defaultPort;  
    }  
   
    public String getFile() {  
        return file;  
    }  
   
    public String getHost() {  
        return host;  
    }  
   
    public String getPath() {  
        return path;  
    }  
   
    public int getPort() {  
        return port;  
    }  
   
    public String getProtocol() {  
        return protocol;  
    }  
   
    public String getQuery() {  
        return query;  
    }  
   
    public String getRef() {  
        return ref;  
    }  
   
    public String getUserInfo() {  
        return userInfo;  
    }  
   
} 