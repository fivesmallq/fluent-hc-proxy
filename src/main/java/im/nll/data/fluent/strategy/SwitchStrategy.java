package im.nll.data.fluent.strategy;

import im.nll.data.fluent.Proxy;

import java.util.List;

/**
 * proxy select strategy
 *
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/4/30 下午11:13
 */
public interface SwitchStrategy {
    Proxy switchy(List<Proxy> proxies);
}
