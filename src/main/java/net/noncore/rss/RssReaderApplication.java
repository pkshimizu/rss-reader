package net.noncore.rss;

import net.noncore.rss.reader.RssReader;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.List;

public class RssReaderApplication {
    public static void main(String[] args) {
        ApplicationArgs appArgs = new ApplicationArgs();
        CmdLineParser parser = new CmdLineParser(appArgs);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            parser.printUsage(System.out);
        }
        RssReader reader = appArgs.getInputResource().createRssReader();
        List<Article> articles = reader.read();
    }
}
