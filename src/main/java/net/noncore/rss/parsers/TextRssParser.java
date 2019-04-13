package net.noncore.rss.parsers;

import net.noncore.rss.Article;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextRssParser implements RssParser {
    @Override
    public List<Article> parse(InputStream input) throws IOException {
        List<Article> articles = new ArrayList<>();
        try (
                Reader reader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);
        ) {
            Pattern titlePattern = Pattern.compile("title: (.*)");
            Pattern bodyPattern = Pattern.compile("body: (.*)");
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                Matcher titleMatcher = titlePattern.matcher(text);
                if (!titleMatcher.matches()) {
                    continue;
                }
                String title = titleMatcher.group(1);
                String bodyText = bufferedReader.readLine();
                if (bodyText == null) {
                    break;
                }
                String body = "";
                Matcher bodyMatcher = bodyPattern.matcher(bodyText);
                if (bodyMatcher.matches()) {
                    body = bodyMatcher.group(1);
                }
                articles.add(new Article(title, body));
            }
        }
        return articles;
    }
}
