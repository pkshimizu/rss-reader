package net.noncore.rss.converters;

public enum ConvertType {
    CUT() {
        @Override
        public ArticleConverter createConverter() {
            return new CutConverter();
        }
    },
    NAME() {
        @Override
        public ArticleConverter createConverter() {
            return new NameConverter();
        }
    };

    public ArticleConverter createConverter() {
        return null;
    }
}
