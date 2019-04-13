package net.noncore.rss.args;

import net.noncore.rss.converters.CompositeArticleConverter;
import net.noncore.rss.converters.ConvertType;
import net.noncore.rss.converters.ArticleConverter;
import net.noncore.rss.writers.ArticleWriter;
import net.noncore.rss.parsers.RssParser;
import net.noncore.rss.readers.RssReader;
import net.noncore.rss.writers.FileArticleWriter;
import net.noncore.rss.writers.StdoutArticleWriter;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationArgs {
    @Option(name = "-i", aliases = "--input", required = true, usage = "Input Resource(file or url)",
            handler = InputResourceHandler.class)
    private InputResource inputResource;
    @Option(name = "-c", aliases = "--convert", required = false, usage = "Convert Types")
    private List<ConvertType> convertTypes;
    @Option(name = "-o", aliases = "--output", required = false, usage = "Output Path")
    private File file;

    public RssReader createRssReader() {
        return inputResource.createRssReader();
    }

    public RssParser createRssParser() {
        return inputResource.createRssParser();
    }

    public ArticleConverter createArticleConverter() {
        if (convertTypes == null) {
            return new CompositeArticleConverter(Collections.emptyList());
        }
        return new CompositeArticleConverter(
                convertTypes.stream().map(ConvertType::createConverter).collect(Collectors.toList()));
    }

    public ArticleWriter createArticleWriter() {
        if (file == null) {
            return new StdoutArticleWriter();
        }
        return new FileArticleWriter(file);
    }
}
