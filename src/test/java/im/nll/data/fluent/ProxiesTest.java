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
        List<String> proxies = Lists.newLinkedList();
        for (int i = 0; i < 10; i++) {
            proxies.add(i + "username:password@127.0.0.1:7777");
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
        Proxies executor = Proxies
                .of(Lists.newArrayList("127.0.0.1:7777", "root:pwd@127.0.0.1:8888"))
                .switchSequence();
        executor.execute(Request.Get("http://www.douban.com"));// will use proxy 1
        executor.execute(Request.Get("http://www.douban.com"));// will use proxy 2
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
