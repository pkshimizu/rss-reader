package net.noncore.rss.reader;

import net.noncore.rss.Article;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRssReader implements RssReader {
    private File file;

    public FileRssReader(File file) {
        this.file = file;
    }

    @Override
    public List<Article> read() {
        List<Article> articles = new ArrayList<>();
        try (
                FileReader reader = new FileReader(file);
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("File not found : %s", file));
        } catch (IOException e) {
            throw new RuntimeException(String.format("File IO error : %s", file));
        }
        return articles;
    }
}
