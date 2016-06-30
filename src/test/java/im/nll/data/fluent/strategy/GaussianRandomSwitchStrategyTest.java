package im.nll.data.fluent.strategy;

import com.google.common.collect.Lists;
import im.nll.data.fluent.Proxy;
import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/6/30 上午2:02
 */
public class GaussianRandomSwitchStrategyTest {
    @Test
    public void switchy() throws Exception {
        GaussianRandomSwitchStrategy randomSwitchStrategy = new GaussianRandomSwitchStrategy();
        List<Proxy> proxies = Lists.newLinkedList();
        for (int i = 0; i < 10; i++) {
            Proxy proxy = new Proxy("127.0.0.1");
            proxies.add(proxy);
        }
        for (int i = 0; i < 100; i++) {
            randomSwitchStrategy.switchy(proxies);
        }
    }


    public void testDelay() throws Exception {
        int count = 20_000; // Generated random numbers
        double lowest = 0;  // For statistics
        double highest = 0;
        double average = 0;
        Random random = new Random();

        for (int i = 0; i < count; ++i) {
            //固定1000毫秒的延迟，500毫秒的波动
            double gaussian = random.nextGaussian() * 15 + 30;
            average += gaussian;
            lowest = Math.min(gaussian, lowest);
            highest = Math.max(gaussian, highest);
            if (i % 10 == 0) { // New line
                System.out.println();
            }
            System.out.printf("%10.4f", gaussian);
        }
        // Display statistics
        System.out.println("\n\nNumber of generated random values following Gaussian distribution: " + count);
        System.out.printf("\nLowest value:  %10.4f\nHighest value: %10.4f\nAverage:       %10.4f", lowest, highest, (average / count));
    }

}
