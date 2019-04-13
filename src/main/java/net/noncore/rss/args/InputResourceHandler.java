package net.noncore.rss.args;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OneArgumentOptionHandler;
import org.kohsuke.args4j.spi.Setter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputResourceHandler extends OneArgumentOptionHandler<InputResource> {
    private CmdLineParser parser;
    public InputResourceHandler(CmdLineParser parser, OptionDef option, Setter<? super InputResource> setter) {
        super(parser, option, setter);
        this.parser = parser;
    }

    @Override
    protected InputResource parse(String argument) throws NumberFormatException, CmdLineException {
        if (Files.exists(Paths.get(argument))) {
            return new InputResource(new File(argument));
        }
        try {
            URL url = new URL(argument);
            return new InputResource(url);
        } catch (MalformedURLException e) {
            throw new CmdLineException(parser, "Invalid input resource", e);
        }
    }
}
