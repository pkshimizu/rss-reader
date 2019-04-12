package net.noncore.rss.reader;

import net.noncore.rss.Article;

import java.util.List;

public interface RssReader {
    List<Article> read();
}
