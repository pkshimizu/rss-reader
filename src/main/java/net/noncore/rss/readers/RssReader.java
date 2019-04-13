package net.noncore.rss.readers;

import java.io.IOException;
import java.io.InputStream;

public interface RssReader {
    InputStream read() throws IOException;
}
