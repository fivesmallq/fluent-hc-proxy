package im.nll.data.fluent;

import org.apache.http.client.fluent.Request;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/4/24 上午11:15
 */
public interface ProxySelector {
    /**
     * custom proxy selection strategy
     *
     * @param request
     * @return
     */
    Proxy select(Request request);
}
