package im.nll.data.fluent;

import com.google.common.collect.Lists;
import im.nll.data.fluent.selector.ProxySelector;
import org.apache.http.client.fluent.Request;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/4/27 下午1:30
 */
public class ProxiesTest {
    @Test
    public void of() throws Exception {
        List<Proxy> proxies = Lists.newLinkedList();
        for (int i = 0; i < 10; i++) {
            Proxy proxy = Proxy.parse(i + "username:password@127.0.0.1:7777");
            proxies.add(proxy);
        }
        ConCurrentUtil.init(10);
        Proxies proxiesExecuter = Proxies.of(proxies)
                .selector(ProxySelector.LOCAL_NO_PROXY_SELECTOR);
        for (int i = 0; i < 100; i++) {
            ConCurrentUtil.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        proxiesExecuter.execute(Request.Get("http://www.baidu.com/"));
                        Thread.sleep(3000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(3000 * 11);
    }

    @Test
    public void executor() throws Exception {

    }

    @Test
    public void execute() throws Exception {

    }

    @Test
    public void execute1() throws Exception {

    }

    @Test
    public void execute2() throws Exception {

    }

}
