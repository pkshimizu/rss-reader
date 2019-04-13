package net.noncore.rss.converters;

import net.noncore.rss.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(JUnit4.class)
public class CutConvertTest {
    private CutConverter converter;

    @Before
    public void setUp() {
        converter = new CutConverter();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void タイトルが10文字未満() {
        Article article = new Article(
                "１２３４５６７８９",
                "１２３４５６７８９０１２３４５６７８９０");
        Article result = converter.convert(article);
        assertThat(result.getTitle(), is("１２３４５６７８９"));
        assertThat(result.getBody(), is("１２３４５６７８９０１２３４５６７８９０"));
    }

    @Test
    public void タイトルが10文字() {
        Article article = new Article(
                "１２３４５６７８９０",
                "１２３４５６７８９０１２３４５６７８９０");
        Article result = converter.convert(article);
        assertThat(result.getTitle(), is("１２３４５６７８９０"));
        assertThat(result.getBody(), is("１２３４５６７８９０１２３４５６７８９０"));

    }

    @Test
    public void タイトルが10文字以上() {
        Article article = new Article(
                "１２３４５６７８９０１",
                "１２３４５６７８９０１２３４５６７８９０");
        Article result = converter.convert(article);
        assertThat(result.getTitle(), is("１２３４５６７８９０"));
        assertThat(result.getBody(), is("１２３４５６７８９０１２３４５６７８９０"));

    }

    @Test
    public void 本文が30文字未満() {
        Article article = new Article(
                "１２３４５６７８９０",
                "１２３４５６７８９０１２３４５６７８９０１２３４５６７８９");
        Article result = converter.convert(article);
        assertThat(result.getTitle(), is("１２３４５６７８９０"));
        assertThat(result.getBody(), is("１２３４５６７８９０１２３４５６７８９０１２３４５６７８９"));

    }

    @Test
    public void 本文が30文字() {
        Article article = new Article(
                "１２３４５６７８９０",
                "１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０");
        Article result = converter.convert(article);
        assertThat(result.getTitle(), is("１２３４５６７８９０"));
        assertThat(result.getBody(), is("１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０"));

    }

    @Test
    public void 本文が30文字以上() {
        Article article = new Article(
                "１２３４５６７８９０",
                "１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１");
        Article result = converter.convert(article);
        assertThat(result.getTitle(), is("１２３４５６７８９０"));
        assertThat(result.getBody(), is("１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０"));
    }
}
