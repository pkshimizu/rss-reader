package net.noncore.rss.readers;

import com.rometools.rome.io.XmlReader;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class NetRssReader implements RssReader {
    private URL url;
    public NetRssReader(URL url) {
        this.url = url;
    }

    @Override
    public Reader read() throws IOException {
        try {
            HttpHead head = new HttpHead(url.toURI());
            HttpClient client = HttpClients.createDefault();
            HttpClientContext context = HttpClientContext.create();
            client.execute(head, context);
            List<URI> redicrectLocations = context.getRedirectLocations();
            if (redicrectLocations != null && !redicrectLocations.isEmpty()) {
                url = redicrectLocations.get(0).toURL();
            }
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
        return new XmlReader(url);
    }
}
