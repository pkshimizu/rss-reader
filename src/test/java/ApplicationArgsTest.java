import net.noncore.rss.ApplicationArgs;
import net.noncore.rss.converters.ConvertType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class ApplicationArgsTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(expected = CmdLineException.class)
    public void testNoArgs() throws CmdLineException {
        ApplicationArgs appArgs = new ApplicationArgs();
        CmdLineParser parser = new CmdLineParser(appArgs);
        parser.parseArgument();
    }
    @Test
    public void testOnlyInput() throws CmdLineException {
        ApplicationArgs appArgs = new ApplicationArgs();
        CmdLineParser parser = new CmdLineParser(appArgs);
        parser.parseArgument("-i", "http://tech.uzabase.com/rss");
        assertThat(appArgs.getInputResource(), is("http://tech.uzabase.com/rss"));
        assertThat(appArgs.getConvertTypes(), is(nullValue()));
        assertThat(appArgs.getOutputPath(), is(nullValue()));
    }
    @Test(expected = CmdLineException.class)
    public void testOnlyConvert() throws CmdLineException {
        ApplicationArgs appArgs = new ApplicationArgs();
        CmdLineParser parser = new CmdLineParser(appArgs);
        parser.parseArgument("-c", "cut");
    }
    @Test(expected = CmdLineException.class)
    public void testOnlyOutput() throws CmdLineException {
        ApplicationArgs appArgs = new ApplicationArgs();
        CmdLineParser parser = new CmdLineParser(appArgs);
        parser.parseArgument("-o", "output.txt");
    }
    @Test
    public void testInputAndConvert() throws CmdLineException {
        ApplicationArgs appArgs = new ApplicationArgs();
        CmdLineParser parser = new CmdLineParser(appArgs);
        parser.parseArgument("-i", "http://tech.uzabase.com/rss", "-c", "name");
        assertThat(appArgs.getInputResource(), is("http://tech.uzabase.com/rss"));
        assertThat(appArgs.getConvertTypes(), is(Collections.singletonList(ConvertType.NAME)));
        assertThat(appArgs.getOutputPath(), is(nullValue()));
    }
    @Test
    public void testInputAndTowConvert() throws CmdLineException {
        ApplicationArgs appArgs = new ApplicationArgs();
        CmdLineParser parser = new CmdLineParser(appArgs);
        parser.parseArgument("-i", "http://tech.uzabase.com/rss", "-c", "name", "-c", "cut");
        assertThat(appArgs.getInputResource(), is("http://tech.uzabase.com/rss"));
        assertThat(appArgs.getConvertTypes(), is(Arrays.asList(ConvertType.NAME, ConvertType.CUT)));
        assertThat(appArgs.getOutputPath(), is(nullValue()));
    }
    @Test(expected = CmdLineException.class)
    public void testInputAndInvalidConvert() throws CmdLineException {
        ApplicationArgs appArgs = new ApplicationArgs();
        CmdLineParser parser = new CmdLineParser(appArgs);
        parser.parseArgument("-i", "http://tech.uzabase.com/rss", "-c", "news");
    }
    @Test
    public void testInputAndOutput() throws CmdLineException {
        ApplicationArgs appArgs = new ApplicationArgs();
        CmdLineParser parser = new CmdLineParser(appArgs);
        parser.parseArgument("-i", "http://tech.uzabase.com/rss", "-o", "output.txt");
        assertThat(appArgs.getInputResource(), is("http://tech.uzabase.com/rss"));
        assertThat(appArgs.getConvertTypes(), is(nullValue()));
        assertThat(appArgs.getOutputPath(), is(new File("output.txt")));
    }
}
