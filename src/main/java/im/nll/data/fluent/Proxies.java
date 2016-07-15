package im.nll.data.fluent;

import com.google.common.collect.Lists;
import im.nll.data.fluent.selector.ProxySelector;
import im.nll.data.fluent.strategy.RandomSwitchStrategy;
import im.nll.data.fluent.strategy.SequenceSwitchStrategy;
import im.nll.data.fluent.strategy.SwitchStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/4/11 下午7:13
 */
public class Proxies {
    private static final Logger LOGGER = LoggerFactory.getLogger(Proxies.class);
    private List<Proxy> proxies;
    private Executor executor = Executor.newInstance();
    private ProxySelector proxySelector = null;
    private SwitchStrategy switchStrategy = new SequenceSwitchStrategy();

    public Proxies(Proxy... proxies) {
        this.proxies = Arrays.asList(proxies);
    }

    public Proxies(List<String> proxies) {
        List<Proxy> list = Lists.newLinkedList();
        for (String proxy : proxies) {
            list.add(Proxy.parse(proxy));
        }
        this.proxies = list;
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
    public static Proxies of(List<String> proxies) {
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
     * set proxy selector.
     *
     * @param selector
     * @return
     */
    public Proxies selector(ProxySelector selector) {
        this.proxySelector = selector;
        return this;
    }

    /**
     * set proxy switch strategy.
     *
     * @param strategy
     * @return
     */
    public Proxies switchStrategy(SwitchStrategy strategy) {
        this.switchStrategy = strategy;
        return this;
    }

    /**
     * set sequence proxy switch strategy.
     *
     * @return
     */
    public Proxies switchSequence() {
        this.switchStrategy = new RandomSwitchStrategy();
        return this;
    }
    /**
     * set random proxy switch strategy.
     *
     * @return
     */
    public Proxies switchRandom() {
        this.switchStrategy = new RandomSwitchStrategy();
        return this;
    }

    /**
     * set gaussian random proxy switch strategy.
     *
     * @return
     */
    public Proxies switchGaussianRandom() {
        this.switchStrategy = new RandomSwitchStrategy();
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
            boolean useProxy = true;
            String urlLine = request.toString();
            if (proxySelector != null) {
                String url = StringUtils.substringBetween(urlLine, " ", " ");
                useProxy = proxySelector.select(new URL(url));
            }
            if (useProxy) {
                HttpHost httpHost = proxy.getHttpHost();
                if (proxy.hasAuthentication()) {
                    executor.auth(httpHost, proxy.getUsername(), proxy.getPassword());
                    executor.authPreemptiveProxy(httpHost);
                }
                LOGGER.info("user proxy: '{}'", proxy);
                request.viaProxy(httpHost);
            }
        }
        return executor.execute(request);
    }


}
