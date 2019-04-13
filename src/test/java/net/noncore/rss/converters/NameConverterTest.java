package net.noncore.rss.converters;

import net.noncore.rss.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class NameConverterTest {
    private NameConverter converter;

    @Before
    public void setUp() {
        converter = new NameConverter();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void 変換なし() {
        Article article = new Article("タイトル", "本文");
        Article result = converter.convert(article);
        assertThat(result.getTitle(), is("タイトル"));
        assertThat(result.getBody(), is("本文"));
    }

    @Test
    public void タイトルにユーザベースを含んでいても変換しない() {
        Article article = new Article("タイトル：ユーザベース", "本文");
        Article result = converter.convert(article);
        assertThat(result.getTitle(), is("タイトル：ユーザベース"));
        assertThat(result.getBody(), is("本文"));
    }

    @Test
    public void 本文にユーザベースを含んでいたら変換する() {
        Article article = new Article("タイトル", "本文：ユーザベース");
        Article result = converter.convert(article);
        assertThat(result.getTitle(), is("タイトル"));
        assertThat(result.getBody(), is("本文：UZABASE"));
    }

    @Test
    public void 本文にユーザベースを複数含んでいる場合() {
        Article article = new Article("タイトル", "本文：ユーザベース・・・ユーザベース");
        Article result = converter.convert(article);
        assertThat(result.getTitle(), is("タイトル"));
        assertThat(result.getBody(), is("本文：UZABASE・・・UZABASE"));
    }
}
