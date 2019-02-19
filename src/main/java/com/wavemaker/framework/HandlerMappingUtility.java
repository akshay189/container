package com.wavemaker.framework;

import com.wavemaker.annotations.Request;

import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HandlerMappingUtility {

    private Map<String, HttpMethodsMapping> mapper = new HashMap<>();

    public Map<String, HttpMethodsMapping> createMap(Set<Class> classes) throws IllegalAccessException, InstantiationException {

        for (Class cls : classes) {
            String url = "";
            if (cls.isAnnotationPresent(Request.class)) {
                Request clsrequest = (Request) cls.getAnnotation(Request.class);
                url = clsrequest.path();
            }
            Method[] methods = cls.getMethods();
            Object instance = cls.newInstance();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Request.class)) {
                    addMethodToMapper(method, url, instance);
                }
            }
        }
        return mapper;
    }

    private void addMethodToMapper(Method method, String url, Object instance) {

        Request request = method.getAnnotation(Request.class);
        url = url + request.path();
        String tempUrl = url;
        url = url.replaceAll("[{]\\w+[}]", "(.+)");
        if (!mapper.containsKey(url)) {
            mapper.put(url, new HttpMethodsMapping());
        }
        mapper.get(url).addHttpMethod(request.method(), method, instance, getMethodParameters(tempUrl, url));
    }

    private List<String> getMethodParameters(String url, String urlPattern) {
        List<String> methodParameters = new ArrayList<>();
        Pattern pattern = Pattern.compile(urlPattern);
        Matcher matcher = pattern.matcher(url);
        int count = 1;
        if (matcher.matches()) {
            while (count <= matcher.groupCount()) {
                methodParameters.add(matcher.group(count++).replaceAll("[{]|[}]", ""));
            }
        }
        if (methodParameters.size() != 0)
            return methodParameters;
        return null;
    }
}
