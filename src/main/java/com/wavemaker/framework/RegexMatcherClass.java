package com.wavemaker.framework;

import com.wavemaker.framework.exceptions.CustomException;
import okhttp3.internal.http2.ErrorCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcherClass {

    public HttpMethodsMapping urlMatcherWithRegexPattern(Map<String, HttpMethodsMapping> mapper, String path, RequestMethod requestMethod) throws CustomException {

        for (Map.Entry<String, HttpMethodsMapping> entry : mapper.entrySet()) {
            Pattern pattern = Pattern.compile(entry.getKey());
            Matcher matcher = pattern.matcher(path);
            if (matcher.matches()) {
                if (matcher.groupCount() != 0) {
                    List<String> list = entry.getValue().getHttpMethodAndJavaMethodMap().get(requestMethod).getMethodParamList();
                    Map<String, String> pathParameters = new HashMap<>();
                    for (int i = 0; i < matcher.groupCount(); i++) {
                        pathParameters.put(list.get(i), matcher.group(i + 1));
                    }
                    entry.getValue().getHttpMethodAndJavaMethodMap().get(requestMethod).setPathParameter(pathParameters);
                }
                return entry.getValue();
            }
        }
        throw new CustomException("NO URL MATCH");//return null;
    }
}
