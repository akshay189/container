import com.wavemaker.framework.HandlerMappingUtility;
import com.wavemaker.framework.RequestMethod;
import com.wavemaker.task.TaskService;
import com.wavemaker.annotations.Request;
import org.junit.Test;

import java.lang.reflect.Method;

public class TestMapperClass {
    @Test
    public void testMapPost() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class cl = TaskService.class;
        Method[] methods = cl.getMethods();
        Method post = null, get = null;
        HandlerMappingUtility handlerMapping = new HandlerMappingUtility();
        for (Method method : methods) {
            Request request = method.getAnnotation(Request.class);
            if (request != null) {
                if ("/".equals(request.path()) && request.method() == RequestMethod.POST) {
                    post = method;
                } else if (request.path().equals("/") && request.method() == RequestMethod.GET) {
                    get = method;
                }
            }
        }
//        Method handlerPostMethod = handlerMapping.getMapper().get("/task/").getHttpMethodAndJavaMethodMap().get(RequestMethod.POST).getMethod();
//        Assert.assertEquals(post, handlerPostMethod);
//        Assert.assertNotEquals(get,handlerPostMethod);
    }
}
