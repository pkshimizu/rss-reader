package net.noncore.rss.converters;

import net.noncore.rss.Article;
import org.apache.commons.lang3.StringUtils;

public class CutConverter implements ArticleConverter {
    private static final int MAX_TITLE_LENGTH = 10;
    private static final int MAX_BODY_LENGTH = 30;

    @Override
    public Article convert(Article article) {
        return new Article(
                cut(article.getTitle(), MAX_TITLE_LENGTH),
                cut(article.getBody(), MAX_BODY_LENGTH));
    }

    private String cut(String text, int maxLength) {
        return StringUtils.left(text, maxLength);
    }
}
