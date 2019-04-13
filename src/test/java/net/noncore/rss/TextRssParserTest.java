package net.noncore.rss;

import net.noncore.rss.parsers.TextRssParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class TextRssParserTest {
    private TextRssParser parser;
    private StringBuffer buffer;

    @Before
    public void setUp() {
        parser = new TextRssParser();
        buffer = new StringBuffer();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void 空のテキスト() throws IOException {
        List<Article> articles = parser.parse(new ByteArrayInputStream(buffer.toString().getBytes("UTF-8")));
        assertThat(articles.size(), is(0));
    }

    @Test
    public void 記事１つ() throws IOException {
        buffer.append("title: タイトル\n");
        buffer.append("body: 本文");
        List<Article> articles = parser.parse(new ByteArrayInputStream(buffer.toString().getBytes("UTF-8")));
        assertThat(articles.size(), is(1));

        Article article1 = articles.get(0);
        assertThat(article1.getTitle(), is("タイトル"));
        assertThat(article1.getBody(), is("本文"));
    }

    @Test
    public void タイトルのみはスキップする() throws IOException {
        buffer.append("title: タイトル");
        List<Article> articles = parser.parse(new ByteArrayInputStream(buffer.toString().getBytes("UTF-8")));
        assertThat(articles.size(), is(0));
    }

    @Test
    public void 本文がないときはスキップする() throws IOException {
        buffer.append("title: タイトル\n");
        List<Article> articles = parser.parse(new ByteArrayInputStream(buffer.toString().getBytes("UTF-8")));
        assertThat(articles.size(), is(0));
    }

    @Test
    public void titleから始まらないときはスキップする() throws IOException {
        buffer.append("body: 本文\n");
        List<Article> articles = parser.parse(new ByteArrayInputStream(buffer.toString().getBytes("UTF-8")));
        assertThat(articles.size(), is(0));
    }

    @Test
    public void 記事２つ() throws IOException {
        buffer.append("title: タイトル1\n");
        buffer.append("body: 本文1\n");
        buffer.append("title: タイトル2\n");
        buffer.append("body: 本文2\n");
        List<Article> articles = parser.parse(new ByteArrayInputStream(buffer.toString().getBytes("UTF-8")));
        assertThat(articles.size(), is(2));

        Article article1 = articles.get(0);
        assertThat(article1.getTitle(), is("タイトル1"));
        assertThat(article1.getBody(), is("本文1"));

        Article article2 = articles.get(1);
        assertThat(article2.getTitle(), is("タイトル2"));
        assertThat(article2.getBody(), is("本文2"));
    }
}
