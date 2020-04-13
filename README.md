# status-check-cli

[Commons CLI](http://commons.apache.org/proper/commons-cli/) を使った簡単な CLI の例。  
任意の URL にリクエストを送り、ステータスコードとメッセージを出力する。

## 使い方

```sh
./gradlew run -P args="-u http://example.com"
```

### オプション

- `-u`, `--url` `<url>`  
  宛先の URL, **必須**
- `-t`, `--trials` `<int>`  
  送信回数, 初期値 1
- `-i`, `--interval` `<int>`  
  送信間隔(sec), 初期値 1
- `-d`, `--delay`  
  始めに遅延する
