package im.nll.data.fluent.strategy;

import im.nll.data.fluent.Proxy;

import java.util.List;
import java.util.Random;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/6/30 上午1:59
 */
public class GaussianRandomSwitchStrategy implements SwitchStrategy {
    protected final Random random = new Random();
    private int range;
    private int delay = 1;

    @Override
    public Proxy switchy(List<Proxy> proxies) {
        int size = proxies.size();
        range = size / 2;
        //System.out.println("gaussian:"+Math.abs(this.random.nextGaussian()));
        int index = (int) Math.abs(this.random.nextGaussian() * range + delay);
        if (index < 0) {
            index = 0;
        }
        if (index > size - 1) {
            index = size - 1;
        }
        return proxies.get(index);
    }
}
