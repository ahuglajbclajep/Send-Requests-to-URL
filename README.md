# send-requests-to-url
Commons CLI で argparse

## Description
任意のurlに接続を試みる

## Usage
### Run
```
gradlew run -P args="-u http://example.com"
```

### Options
 * `-u`,`--url` `<url>` _宛先のURL, 必須_
 * `-t`,`--trials` `<int>` _送信回数, 初期値1_
 * `-i`,`--interval` `<int>` _送信間隔(sec), 初期値1_
 * `-d`,`--delay` _始めに遅延する_

送信回数1回かつ間隔が1秒で遅延なしのとき最速で実行

## License
[MIT](LICENSE)
