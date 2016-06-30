package im.nll.data.fluent;

import im.nll.data.fluent.strategy.SequenceSwitchStrategy;
import im.nll.data.fluent.strategy.SwitchStrategy;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/4/11 下午7:13
 */
public class Proxies {
    private List<Proxy> proxies;
    private Executor executor = Executor.newInstance();
    private ProxySelector proxySelector = new DefaultProxySelector();
    private SwitchStrategy switchStrategy = new SequenceSwitchStrategy();

    public Proxies(Proxy... proxies) {
        this.proxies = Arrays.asList(proxies);
    }

    public Proxies(List<Proxy> proxies) {
        this.proxies = proxies;
    }

    /**
     * create instance with proxies.
     *
     * @param proxies
     * @return
     */
    public static Proxies of(Proxy... proxies) {
        return new Proxies(proxies);
    }

    /**
     * create instance with proxies.
     *
     * @param proxies
     * @return
     */
    public static Proxies of(List<Proxy> proxies) {
        return new Proxies(proxies);
    }

    /**
     * create proxies instance with proxy string.
     *
     * @param proxy
     * @return
     */
    public static Proxies of(String proxy) {
        return new Proxies(Proxy.parse(proxy));
    }

    public Proxies executor(Executor executor) {
        this.executor = executor;
        return this;
    }

    /**
     * execute request use default proxy
     *
     * @param request
     * @return
     * @throws IOException
     */
    public Response execute(Request request) throws IOException {
        return execute(switchStrategy.switchy(proxies), request);
    }

    public Response execute(String proxy, Request request) throws IOException {
        Proxy p = Proxy.parse(proxy);
        return execute(p, request);
    }

    public Response execute(Proxy proxy, Request request) throws IOException {
        if (proxy != null) {
            HttpHost httpHost = proxy.getHttpHost();
            if (proxy.hasAuthentication()) {
                executor.auth(httpHost, proxy.getUsername(), proxy.getPassword());
                executor.authPreemptiveProxy(httpHost);
            }
            request.viaProxy(httpHost);
        }
        return executor.execute(request);
    }


}
