#fluent-hc-proxy
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/im.nll.data/fluent-hc-proxy/badge.svg)](https://maven-badges.herokuapp.com/maven-central/im.nll.data/fluent-hc-proxy/)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

Simplify switching multiple proxies.

# Futures:

* proxy switch strategy: sequence, random, gussian random.
* proxy selector: chosen which url use proxy
* proxy author simplify.

# Usage
To add a dependency on Web-Data-Extractor using Maven, use the following:

```xml
<dependency>
    <groupId>im.nll.data</groupId>
    <artifactId>fluent-hc-proxy</artifactId>
    <version>0.9.1</version>
</dependency>
```

To add a dependency using Gradle:

```
dependencies {
  compile 'im.nll.data:fluent-hc-proxy:0.9.1'
}
```

# Examples

### request url with different proxy.

````java
     Proxies executor=Proxies
        .of(Lists.newArrayList("127.0.0.1:7777","root:pwd@127.0.0.1:8888"))
        .switchSequence();
        executor.execute(Request.Get("http://www.douban.com"));// will use proxy 1
        executor.execute(Request.Get("http://www.douban.com"));// will use proxy 2
````

### request url with proxy simple

```java
   String proxy="root:pwd@127.0.0.1:8888";
        HttpResponse httpResponse=Proxies.of(proxy).execute(Request.Get("http://httpbin.org/anything")).returnResponse();

```

# Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/fivesmallq/fluent-hc-proxy.
