package net.noncore.rss;

import net.noncore.rss.args.ApplicationArgs;
import net.noncore.rss.converters.CompositeRssConverter;
import net.noncore.rss.writers.StdoutRssWriter;
import net.noncore.rss.readers.FileRssReader;
import net.noncore.rss.readers.NetRssReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class ApplicationArgsTest {
    ApplicationArgs appArgs;
    CmdLineParser parser;

    @Before
    public void setUp() {
        appArgs = new ApplicationArgs();
        parser = new CmdLineParser(appArgs);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = CmdLineException.class)
    public void 引数なし() throws CmdLineException {
        parser.parseArgument();
    }
    @Test
    public void inputでURL指定() throws CmdLineException {
        parser.parseArgument("-i", "http://tech.uzabase.com/rss");
        assertThat(appArgs.createRssReader(), is(instanceOf(NetRssReader.class)));
        assertThat(appArgs.createRssConverter(), is(instanceOf(CompositeRssConverter.class)));
        assertThat(appArgs.createRssWriter(), is(instanceOf(StdoutRssWriter.class)));
    }
    @Test
    public void inputでファイル指定() throws CmdLineException {
        parser.parseArgument("-i", "src/test/resources/articles.txt");
        assertThat(appArgs.createRssReader(), is(instanceOf(FileRssReader.class)));
        assertThat(appArgs.createRssConverter(), is(instanceOf(CompositeRssConverter.class)));
        assertThat(appArgs.createRssWriter(), is(instanceOf(StdoutRssWriter.class)));
    }
    @Test(expected = CmdLineException.class)
    public void inputなしでconvert指定() throws CmdLineException {
        parser.parseArgument("-c", "cut");
    }
    @Test(expected = CmdLineException.class)
    public void inputなしでoutput指定() throws CmdLineException {
        parser.parseArgument("-o", "output.txt");
    }
    @Test
    public void convert１つ指定() throws CmdLineException {
        parser.parseArgument("-i", "http://tech.uzabase.com/rss", "-c", "name");
        assertThat(appArgs.createRssReader(), is(instanceOf(NetRssReader.class)));
        assertThat(appArgs.createRssConverter(), is(instanceOf(CompositeRssConverter.class)));
        assertThat(appArgs.createRssWriter(), is(instanceOf(StdoutRssWriter.class)));
    }
    @Test
    public void inputとconvert２つ指定() throws CmdLineException {
        parser.parseArgument("-i", "http://tech.uzabase.com/rss", "-c", "name", "-c", "cut");
        assertThat(appArgs.createRssReader(), is(instanceOf(NetRssReader.class)));
        assertThat(appArgs.createRssConverter(), is(instanceOf(CompositeRssConverter.class)));
        assertThat(appArgs.createRssWriter(), is(instanceOf(StdoutRssWriter.class)));
    }
    @Test(expected = CmdLineException.class)
    public void 存在しないinputを指定() throws CmdLineException {
        parser.parseArgument("-i", "http://tech.uzabase.com/rss", "-c", "news");
    }
    @Test
    public void すべての引数を指定() throws CmdLineException {
        parser.parseArgument("-i", "http://tech.uzabase.com/rss", "-o", "output.txt");
        assertThat(appArgs.createRssReader(), is(instanceOf(NetRssReader.class)));
        assertThat(appArgs.createRssConverter(), is(instanceOf(CompositeRssConverter.class)));
        assertThat(appArgs.createRssWriter(), is(instanceOf(StdoutRssWriter.class)));
    }
}
