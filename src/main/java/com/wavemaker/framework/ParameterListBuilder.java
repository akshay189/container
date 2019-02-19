package com.wavemaker.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wavemaker.annotations.PathVariable;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParameterListBuilder {

    public List<MethodParameterDetails> buildParameterList(MethodAndClassInstancePair methodAndClassInstancePair) {
        Method method = methodAndClassInstancePair.getMethod();
        return generateParameterDetailsList(method.getParameterTypes(), method.getParameterAnnotations());
    }

    public Object[] getParameters(List<MethodParameterDetails> methodParameterDetails, Map<String, String> parameterNameAndValueMap, HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object[] args = new Object[methodParameterDetails.size()];
        int count = 0;
        for (MethodParameterDetails parameterDetails : methodParameterDetails) {
            if (parameterDetails.getParameterType() == ParameterType.Path) {
                args[count++] = objectMapper.convertValue(parameterNameAndValueMap.get(parameterDetails.getName()), parameterDetails.getJavaDataType());
            } else if (parameterDetails.getParameterType() == ParameterType.Body) {
                args[count++] = objectMapper.readValue(request.getInputStream(), parameterDetails.getJavaDataType());
            }
        }
        if (args.length != 0)
            return args;
        return null;
    }

    private List<MethodParameterDetails> generateParameterDetailsList(Class[] parameterType, Annotation[][] annotations) {
        List<MethodParameterDetails> listOfParameterDetails = new ArrayList<>();
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                listOfParameterDetails.add(getDataFromAnnotation(annotations[i][j]));
            }
        }
        for (int i = 0; i < listOfParameterDetails.size(); i++) {
            listOfParameterDetails.get(i).setJavaDataType(parameterType[i]);
        }
        return listOfParameterDetails;
    }

    private MethodParameterDetails getDataFromAnnotation(Annotation annotation) {
        MethodParameterDetails parameterDetails = new MethodParameterDetails();
        if (annotation.annotationType().getSimpleName().equals("PathVariable")) {
            PathVariable pathVariable = (PathVariable) annotation;
            parameterDetails.setName(pathVariable.value());
            parameterDetails.setParameterType(ParameterType.Path);

        } else if (annotation.annotationType().getSimpleName().equals("RequestBody")) {
            parameterDetails.setName("Body");
            parameterDetails.setParameterType(ParameterType.Body);
        }
        return parameterDetails;
    }
}
