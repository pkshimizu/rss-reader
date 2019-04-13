package net.noncore.rss.args;

import net.noncore.rss.converters.CompositeRssConverter;
import net.noncore.rss.converters.ConvertType;
import net.noncore.rss.converters.RssConverter;
import net.noncore.rss.writers.RssWriter;
import net.noncore.rss.parsers.RssParser;
import net.noncore.rss.readers.RssReader;
import net.noncore.rss.writers.StdoutRssWriter;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationArgs {
    @Option(name = "-i", aliases = "--input", required = true, usage = "Input Resource(file or url)",
            handler = InputResourceHandler.class)
    private InputResource inputResource;
    @Option(name = "-c", aliases = "--converts", required = false, usage = "Convert Types")
    private List<ConvertType> convertTypes;
    @Option(name = "-o", aliases = "--output", required = false, usage = "Output Path")
    private File outputPath;

    @Override
    public String toString() {
        return String.format(
                "Input Resource: %s, Converter Types: %s, Ouput Path: %s",
                inputResource,
                convertTypes,
                outputPath);
    }

    public RssReader createRssReader() {
        return inputResource.createRssReader();
    }

    public RssParser createRssParser() {
        return inputResource.createRssParser();
    }

    public RssConverter createRssConverter() {
        if (convertTypes == null) {
            return new CompositeRssConverter(Collections.emptyList());
        }
        return new CompositeRssConverter(
                convertTypes.stream().map(ConvertType::createConverter).collect(Collectors.toList()));
    }

    public RssWriter createRssWriter() {
        return new StdoutRssWriter();
    }
}
