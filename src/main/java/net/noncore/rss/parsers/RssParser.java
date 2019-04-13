package net.noncore.rss.parsers;

import net.noncore.rss.Article;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public interface RssParser {
    List<Article> parse(Reader reader) throws IOException;
}
