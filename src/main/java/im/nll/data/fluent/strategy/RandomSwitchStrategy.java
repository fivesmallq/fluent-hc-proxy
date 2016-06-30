package im.nll.data.fluent.strategy;

import im.nll.data.fluent.Proxy;

import java.util.List;
import java.util.Random;

/**
 * switch proxy use java random.
 *
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/6/30 上午1:50
 */
public class RandomSwitchStrategy implements SwitchStrategy {
    private Random random = new Random();

    @Override
    public Proxy switchy(List<Proxy> proxies) {
        int index = random.nextInt(proxies.size());
        return proxies.get(index);
    }
}
