package im.nll.data.fluent.strategy;

import com.google.common.collect.Lists;
import im.nll.data.fluent.Proxy;
import org.junit.Test;

import java.util.List;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/6/30 上午1:53
 */
public class RandomSwitchStrategyTest {
    @Test
    public void switchy() throws Exception {
        RandomSwitchStrategy randomSwitchStrategy = new RandomSwitchStrategy();
        List<Proxy> proxies = Lists.newLinkedList();
        for (int i = 0; i < 10; i++) {
            Proxy proxy = new Proxy("127.0.0.1");
            proxies.add(proxy);
        }
        for (int i = 0; i < 100; i++) {
            randomSwitchStrategy.switchy(proxies);
        }
    }

}
