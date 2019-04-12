package net.noncore.rss;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class RssReaderApplication {
    public static void main(String[] args) {
        ApplicationArgs appArgs = new ApplicationArgs();
        CmdLineParser parser = new CmdLineParser(appArgs);
        try {
            parser.parseArgument(args);
            System.out.println(appArgs.toString());
        } catch (CmdLineException e) {
            parser.printUsage(System.out);
        }
    }
}
