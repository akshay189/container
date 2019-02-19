package com.wavemaker.framework;

public class MethodParameterDetails {
    private String name;
    private ParameterType parameterType;
    private Class javaDataType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public void setParameterType(ParameterType parameterType) {
        this.parameterType = parameterType;
    }

    public Class getJavaDataType() {
        return javaDataType;
    }

    public void setJavaDataType(Class javaDataType) {
        this.javaDataType = javaDataType;
    }
}
