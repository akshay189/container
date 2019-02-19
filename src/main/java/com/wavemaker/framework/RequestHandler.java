package com.wavemaker.framework;


import com.google.gson.Gson;
import com.wavemaker.framework.exceptions.CustomException;
import com.wavemaker.task.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class RequestHandler {

    private Map<String, HttpMethodsMapping> urlAndMethodObjectMapper = null;
    private RegexMatcherClass regexMatcherClass = new RegexMatcherClass();
    private Gson gson = new Gson();

    public RequestHandler() throws InstantiationException, IllegalAccessException {
        Set<Class> classesToBeTracked = new HashSet<>(Arrays.asList(TaskService.class));
        HandlerMappingUtility handlerMapping = new HandlerMappingUtility();
        urlAndMethodObjectMapper = handlerMapping.createMap(classesToBeTracked);
    }

    public Object handleRequest(String path, HttpServletRequest request, RequestMethod requestMethod) throws InvocationTargetException, IllegalAccessException, IOException, CustomException {
        MethodAndClassInstancePair methodWithClassInstance;
        Object result;
        try {
            methodWithClassInstance = regexMatcherClass.urlMatcherWithRegexPattern(urlAndMethodObjectMapper, path, requestMethod).getHttpMethodAndJavaMethodMap().get(requestMethod);
            ParameterListBuilder parameterListBuilder = new ParameterListBuilder();
            List<MethodParameterDetails> listOfParameters = parameterListBuilder.buildParameterList(methodWithClassInstance);
            result = methodWithClassInstance.getMethod().invoke(methodWithClassInstance.getObject(), parameterListBuilder.getParameters(listOfParameters, methodWithClassInstance.getPathParameter(), request));

        } catch (CustomException | IllegalAccessException | InvocationTargetException e) {
            throw new CustomException("exception caused in Request Handler", e);
        }

        return gson.toJson(result);
    }
}
