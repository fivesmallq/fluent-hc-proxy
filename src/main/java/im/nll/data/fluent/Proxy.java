package im.nll.data.fluent;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;

/**
 * Simple wrapper for a proxy in form [username[:password]]@host[:port]
 *
 * @author daniel
 */
public class Proxy {

    static final Integer DEFAULT_PORT = 80;
    private static final CommonProxyParser PARSER = new CommonProxyParser();

    private String username;
    private String password;
    private String ip;
    private Integer port;

    /**
     * Convenience-method even thu it creates a cyclic dependency to
     * CommonProxyParser
     */
    public static Proxy parse(String proxy) {
        return PARSER.parse(proxy);
    }

    public Proxy(String ip) {
        this(ip, null, null, null);
    }

    public Proxy(String ip, Integer port) {
        this(ip, port, null, null);
    }

    public Proxy(String ip, Integer port, String username, String password) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean hasAuthentication() {
        return StringUtils.isNotBlank(username)
                && StringUtils.isNotBlank(password);
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port == null ? DEFAULT_PORT : port;
    }

    /**
     * get httpclient httphost
     *
     * @return
     */
    public HttpHost getHttpHost() {
        return new HttpHost(getIp(), getPort());
    }

    @Override
    public String toString() {
        String result = "";
        if (!StringUtils.isBlank(getIp())) {
            StringBuilder builder = new StringBuilder();
            if (StringUtils.isNotBlank(getUsername())) {
                builder.append(getUsername());
                if (StringUtils.isNotBlank(getPassword())) {
                    builder.append(":");
                    builder.append(getPassword());
                }
                builder.append("@");
            }
            builder.append(getIp());
            builder.append(":");
            builder.append(getPort());

            result = builder.toString();
        }
        return result;
    }

}
