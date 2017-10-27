# Send-Requests-to-URL
Commons CLI で argparse

## Description
任意のurlに接続を試みる

## Usage
### Run
```sh
./gradlew run -P args="-u http://example.com"
```

### Options
* `-u`, `--url` `<url>` 宛先のURL, **必須**
* `-t`, `--trials` `<int>` 送信回数, 初期値1
* `-i`, `--interval` `<int>` 送信間隔(sec), 初期値1
* `-d`, `--delay` 始めに遅延する

送信回数1回かつ間隔が1秒で遅延なしのとき最速で実行

## License
[MIT](LICENSE)
