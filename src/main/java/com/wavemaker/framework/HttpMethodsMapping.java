package com.wavemaker.framework;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpMethodsMapping {
    private Map<RequestMethod, MethodAndClassInstancePair> httpMethodAndJavaMethodMap = new HashMap<>();

    public Map<RequestMethod, MethodAndClassInstancePair> getHttpMethodAndJavaMethodMap() {
        return httpMethodAndJavaMethodMap;
    }

    public void addHttpMethod(RequestMethod requestMethod, Method method, Object object, List<String> methodParamList) {
        httpMethodAndJavaMethodMap.put(requestMethod, new MethodAndClassInstancePair(method, object, methodParamList));
    }
}
