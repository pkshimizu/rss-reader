package net.noncore.rss.parsers;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import net.noncore.rss.Article;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

public class XmlRssParser implements RssParser {
    @Override
    public List<Article> parse(Reader reader) throws IOException {
        try {
            SyndFeedInput input = new SyndFeedInput();
            input.setAllowDoctypes(true);
            SyndFeed feed = input.build(reader);
            return feed.getEntries().stream().map(entry ->
                    new Article(entry.getTitle(), entry.getDescription().getValue())).collect(Collectors.toList());
        } catch (FeedException e) {
            throw new IOException(e);
        }
    }
}
