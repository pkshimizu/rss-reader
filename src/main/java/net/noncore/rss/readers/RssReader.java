package net.noncore.rss.readers;

import java.io.IOException;
import java.io.Reader;

public interface RssReader {
    Reader read() throws IOException;
}
