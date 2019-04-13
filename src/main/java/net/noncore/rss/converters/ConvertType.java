package net.noncore.rss.converters;

public enum ConvertType {
    CUT() {
        @Override
        public RssConverter createConverter() {
            return null;
        }
    },
    NAME() {
        @Override
        public RssConverter createConverter() {
            return null;
        }
    };

    public RssConverter createConverter() {
        return null;
    }
}
