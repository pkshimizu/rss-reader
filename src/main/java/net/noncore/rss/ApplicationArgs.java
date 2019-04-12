package net.noncore.rss;

import lombok.Getter;
import net.noncore.rss.converters.ConvertType;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.List;

@Getter
public class ApplicationArgs {
    @Option(name = "-i", aliases = "--input", required = true, usage = "Input Resource")
    private String inputResource;
    @Option(name = "-c", aliases = "--converts", required = false, usage = "Convert Types(cut, name)", handler = ConvertTypesOptionHandler.class)
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
}
