package net.noncore.rss;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class Article {
    private String title;
    private String body;
}
