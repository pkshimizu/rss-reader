package net.noncore.rss;

import net.noncore.rss.args.ApplicationArgs;
import net.noncore.rss.converters.ArticleConverter;
import net.noncore.rss.writers.ArticleWriter;
import net.noncore.rss.parsers.RssParser;
import net.noncore.rss.readers.RssReader;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RssReaderApplication {
    public static void main(String[] args) throws IOException {
        ApplicationArgs appArgs = new ApplicationArgs();
        try {
            CmdLineParser parser = new CmdLineParser(appArgs);
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.getParser().printUsage(System.out);
            return;
        }
        RssReader reader = appArgs.createRssReader();
        RssParser parser = appArgs.createRssParser();
        List<Article> articles = parser.parse(reader.read());

        ArticleConverter converter = appArgs.createArticleConverter();
        articles = articles.stream().map(converter::convert).collect(Collectors.toList());

        ArticleWriter writer = appArgs.createArticleWriter();
        writer.write(articles);
    }
}
