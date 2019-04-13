package net.noncore.rss.readers;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileRssReader implements RssReader {
    private File file;

    public FileRssReader(File file) {
        this.file = file;
    }

    @Override
    public Reader read() throws IOException {
        return new FileReader(file);
    }
}
