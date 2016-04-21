package im.nll.data.fluent;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/4/11 下午7:13
 */
public class Proxies {
    Executor executor = Executor.newInstance();

    public Request with(String proxy, Request request) {
        return request;
    }
}
