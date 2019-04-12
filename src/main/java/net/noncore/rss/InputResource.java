package net.noncore.rss;

import net.noncore.rss.reader.FileRssReader;
import net.noncore.rss.reader.NetRssReader;
import net.noncore.rss.reader.RssReader;

import java.io.File;
import java.net.URL;

public class InputResource {
    private File file;
    private URL url;

    public InputResource(File file) {
        this.file = file;
        this.url = null;
    }

    public InputResource(URL url) {
        this.url = url;
        this.file = null;
    }

    public RssReader createRssReader() {
        if (file != null) {
            return new FileRssReader(file);
        }
        if (url != null) {
            return new NetRssReader(url);
        }
        throw new RuntimeException("Unsupport input resource.");
    }
}
