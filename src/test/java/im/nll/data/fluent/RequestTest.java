package im.nll.data.fluent;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @version Revision: 1.0
 * @date 16/4/11 下午6:44
 */
public class RequestTest {
    private Logger logger = LoggerFactory.getLogger(RequestTest.class);
    Executor executor = Executor.newInstance();

    public void test() throws IOException, InterruptedException {
        String file = com.google.common.io.Resources.getResource("proxy.txt").getFile();
        List<String> proxies = Files.readLines(new File(file), Charsets.UTF_8);
        List<HttpHost> httpHosts = new ArrayList<>();
        for (String proxyString : proxies) {
            HttpHost proxy = new HttpHost(proxyString, 8888);
            httpHosts.add(proxy);
        }
        executor.clearAuth();
        ConCurrentUtil.init(10);
        for (int i = 0; i < 100; i++) {
            int random = new Random().nextInt(httpHosts.size());
            HttpHost proxy = httpHosts.get(random);
            ConCurrentUtil.execute(new TestJob(proxy));
        }
        Thread.sleep(30000);
    }

    private static String readDataFromConsole(String prompt) {
        String data = "Hello, World!\r\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);
            System.out.println(scanner.nextLine());
        } finally {
            System.setIn(stdin);
        }
        return "";
    }

    private static void usingScanner() {
        System.out.println("Name: ");

        Scanner scanIn = new Scanner(System.in);
        String inputString = scanIn.nextLine();

        scanIn.close();
        System.out.println("Name entered : " + inputString);
    }

    public void test3() throws IOException, InterruptedException {
        String salt = UUID.randomUUID().toString();
        usingScanner();
        String password = "abc123xxx";
        String fixedSalt = "xfq";
        String result = Hashing.sha256().newHasher().putString(salt + password + fixedSalt, Charsets.UTF_8).hash().toString();
        System.out.println(result);
    }

    public void test2() throws IOException, InterruptedException {
        String url = "http://www.douban.com";
        String html = (executor.execute(
                Request.Get(url)).returnContent().asString());
        System.out.println(html);
    }

    class TestJob implements Runnable {
        private HttpHost proxy;

        public TestJob(HttpHost proxy) {
            this.proxy = proxy;
        }

        @Override
        public void run() {
            String url = "http://zh.infobyip.com/detectproxy.php";
            try {
                executor.auth(proxy, "novacloud", "novacloud2016");
                executor.authPreemptiveProxy(proxy);
                String html = (executor.execute(
                        Request.Get(url).viaProxy(proxy)).returnContent().asString());
                String proxyIp = (Jsoup.parse(html).select("body > table > tbody > tr:nth-child(3) > td > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(2)").first().text());
                logger.info(proxy.getHostName() + "->" + proxyIp + "\t{}", proxy.getHostName().equals(proxyIp));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error proxy:" + proxy.getHostName());
            }
        }
    }
}
