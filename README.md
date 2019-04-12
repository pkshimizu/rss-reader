# RSSリーダー

## 実行方法
```sh
$ java -jar build/libs/RssReader-1.0.jar -i http://tech.uzabase.com/ -c cut -o result.txt
$ java -jar build/libs/RssReader-1.0.jar -i http://tech.uzabase.com/ -c cut -c name -o result.txt
```

## ビルド手順
```sh
$ gradle jar
```
