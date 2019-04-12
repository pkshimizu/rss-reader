package net.noncore.rss;

import com.sun.tools.javac.util.StringUtils;
import net.noncore.rss.converters.ConvertType;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OneArgumentOptionHandler;
import org.kohsuke.args4j.spi.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertTypesOptionHandler extends OneArgumentOptionHandler<List<ConvertType>> {

    public ConvertTypesOptionHandler(CmdLineParser parser, OptionDef option, Setter<? super List<ConvertType>> setter) {
        super(parser, option, setter);
    }

    @Override
    protected List<ConvertType> parse(String argument) throws NumberFormatException {
        String[] args = argument.split(",");
        return Stream.of(args)
                .map(arg -> ConvertType.valueOf(StringUtils.toUpperCase(arg)))
                .collect(Collectors.toList());
    }
}
