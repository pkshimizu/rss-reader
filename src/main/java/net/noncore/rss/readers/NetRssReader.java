package net.noncore.rss.readers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class NetRssReader implements RssReader {
    private URL url;
    public NetRssReader(URL url) {
        this.url = url;
    }

    @Override
    public InputStream read() throws IOException {
        return null;
    }
}
