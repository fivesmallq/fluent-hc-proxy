package im.nll.data.fluent.strategy;

import im.nll.data.fluent.Proxy;

import java.util.List;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/6/30 上午2:22
 */
public class SequenceSwitchStrategy implements SwitchStrategy {
    private int index = 0;

    @Override
    public synchronized Proxy switchy(List<Proxy> proxies) {
        Proxy proxy = proxies.get(index);
        index++;
        if (index > proxies.size() - 1) {
            index = 0;
        }
        return proxy;
    }
}
