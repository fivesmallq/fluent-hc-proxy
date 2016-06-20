package im.nll.data.fluent;

import org.apache.http.client.fluent.Request;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/6/20 下午5:09
 */
public class DefaultProxySelector implements ProxySelector {
    @Override
    public Proxy select(Request request) {
        return null;
    }
}
