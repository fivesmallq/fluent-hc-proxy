package im.nll.data.fluent;

import im.nll.data.fluent.selector.ProxySelector;
import org.apache.http.client.fluent.Request;
import org.junit.Test;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/4/27 下午1:30
 */
public class ProxiesTest {
    @Test
    public void of() throws Exception {
        Proxies.of("127.0.0.1:7777")
                .selector(ProxySelector.LOCAL_NO_PROXY_SELECTOR)
                .switchRandom()
                .execute(Request.Get("http://www.baidu.com/"));
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
