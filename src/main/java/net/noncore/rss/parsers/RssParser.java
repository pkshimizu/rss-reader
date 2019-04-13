package net.noncore.rss.parsers;

import net.noncore.rss.Article;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface RssParser {
    List<Article> parse(InputStream input) throws IOException;
}
