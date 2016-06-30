package im.nll.data.fluent.selector;

import com.google.common.collect.Lists;

import java.net.URL;
import java.util.List;

/**
 * decide use proxy or not by url,if not return false;
 *
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/4/24 上午11:15
 */
public interface ProxySelector {
    /**
     * return proxy use proxy or not.
     *
     * @param url
     * @return
     */
    boolean select(URL url);

    /**
     * A selector that always selects no proxy.
     */
    ProxySelector NO_PROXY_SELECTOR = new ProxySelector() {
        @Override
        public boolean select(URL url) {
            return false;
        }
    };

    /**
     * A selector that always selects no proxy with local host.
     */
    ProxySelector LOCAL_NO_PROXY_SELECTOR = new ProxySelector() {
        private List<String> noProxyHost = Lists.newArrayList();

        @Override
        public boolean select(URL url) {
            noProxyHost.add("localhost");
            noProxyHost.add("127.0.0.1");
            noProxyHost.add("0.0.0.0");
            String host = url.getHost();
            return !noProxyHost.contains(host);
        }
    };
}
