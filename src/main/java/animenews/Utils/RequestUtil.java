package animenews.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {
    private static Map<Long, HttpServletRequest> requests = new HashMap<>();


    public static HttpServletRequest getRequest(Long currentThreadId) {
        return requests.get(currentThreadId);
    }

    public static boolean addRequest(HttpServletRequest request) {
        Long currentThreadId = (Long) request.getAttribute("currentThreadId");
        if (requests.containsKey(currentThreadId)) {
            return false;
        }
        requests.put(currentThreadId, request);
        return true;
    }

    public static boolean removeRequest(Long currentThreadId) {
        if (!requests.containsKey(currentThreadId)) {
            return false;
        }
        requests.remove(currentThreadId);
        return true;
    }
}
