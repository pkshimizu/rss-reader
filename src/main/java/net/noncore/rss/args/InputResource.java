package net.noncore.rss.args;

import net.noncore.rss.parsers.RssParser;
import net.noncore.rss.parsers.TextRssParser;
import net.noncore.rss.parsers.XmlRssParser;
import net.noncore.rss.readers.FileRssReader;
import net.noncore.rss.readers.NetRssReader;
import net.noncore.rss.readers.RssReader;

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

    public RssParser createRssParser() {
        if (file != null) {
            return new TextRssParser();
        }
        if (url != null) {
            return new XmlRssParser();
        }
        throw new RuntimeException("Unsupport input resource.");
    }
}
