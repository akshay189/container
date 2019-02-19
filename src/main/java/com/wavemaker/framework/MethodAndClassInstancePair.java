package com.wavemaker.framework;


import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class MethodAndClassInstancePair {
    private Method method;
    private Object object;
    private List<String> methodParamList;
    private Map<String, String> pathParameter;

    public MethodAndClassInstancePair(Method method, Object object, List<String> methodParamList) {
        this.method = method;
        this.object = object;
        this.methodParamList = methodParamList;
    }


    public Method getMethod() {
        return method;
    }

    public Object getObject() {
        return object;
    }

    public List<String> getMethodParamList() {
        return methodParamList;
    }

    public Map<String, String> getPathParameter() {
        return pathParameter;
    }

    public void setPathParameter(Map<String, String> pathParameter) {
        this.pathParameter = pathParameter;
    }

    @Override
    public String toString() {
        return "MethodAndClassInstancePair{" +
                "method=" + method +
                ", object=" + object +
                ", methodParamList=" + methodParamList +
                '}';
    }
}
