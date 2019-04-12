# RSSリーダー

## 設問
```text
ある RSS フィードまたはファイルを取り込み、いくつかの変換処理を行い、出力するアプリケー
ションを制作します。下記の仕様・要求に沿ったアプリケーションのソースコードを提出してくださ
い。また、クラス図も提出してください。
```
- 入力A : RSS の URL(例、 http://tech.uzabase.com/rss )
- 入力B : タイトルと本文が書かれたファイル(例、同梱の articles.txt)
- 変換A : タイトルを 10 文字、本文を 30 文字にカットする。
- 変換B : 本文中に表示される「ユーザベース」 をすべて「UZABASE」に変換する。
- 出力A : 変換したタイトルと本文を、標準出力に出力する。
- 出力B : 変換したタイトルと本文を、指定したファイルに出力する。
- 上記の機能を、コマンドライン引数で自由に設定して、実行することができる。

```text
データの流れをどのように処理するか。オブジェクト指向に基づき、処理を担うクラスの責務を
分けて設計、実装できるか最も重視して採点します。
```

### 実行例
(1) http://tech.uzabase.com/ を読み込み A の変換だけ行い result.txt に出力する。
```bash
java RssReader -i http://tech.uzabase.com/ -c cut -o result.txt
```
(2) articles.txt を読み込んで、A と B の変換を行い標準出力に出力する。
```bash
java RssReader -i articles.txt -c cut,convert
```

## 実行方法
```sh
$ java -jar build/libs/RssReader-1.0.jar -i http://tech.uzabase.com/ -c cut -o result.txt
$ java -jar build/libs/RssReader-1.0.jar -i http://tech.uzabase.com/ -c cut -c name -o result.txt
```

## ビルド手順
```sh
$ gradle jar
```
