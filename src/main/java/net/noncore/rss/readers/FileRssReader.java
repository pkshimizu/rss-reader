package net.noncore.rss.readers;

import java.io.*;

public class FileRssReader implements RssReader {
    private File file;

    public FileRssReader(File file) {
        this.file = file;
    }

    @Override
    public InputStream read() throws IOException {
        return new FileInputStream(file);
    }
}
