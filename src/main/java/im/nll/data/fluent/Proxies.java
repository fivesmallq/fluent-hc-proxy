package im.nll.data.fluent;

import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import java.io.IOException;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/4/11 下午7:13
 */
public class Proxies {
    Executor executor = Executor.newInstance();

    public Response execute(String proxy, Request request) throws IOException {
        Proxy p = Proxy.parse(proxy);
        return execute(p, request);
    }

    public Response execute(Proxy proxy, Request request) throws IOException {
        HttpHost httpHost = proxy.getHttpHost();
        executor.auth(httpHost, proxy.getUsername(), proxy.getPassword());
        executor.authPreemptiveProxy(httpHost);
        request.viaProxy(httpHost);
        return executor.execute(request);
    }
}
