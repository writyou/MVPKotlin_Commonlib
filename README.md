# 本demo纯学习之用 不可作为任何商业用途
# 一套完整的数字货币与法币汇率转换以及货币符号匹配展示规则demo
# KotlinMvp 纯demo 无杂质 的MVP基础框架

#### 1. 法币价格数据展示规则
- x >= 1000 0 0000		无小数 //容错，暂时没有可能发生
- 100 <= x < 1000 0 0000	2位小数 //例：$12345.12
- 0.1<= x < 100		4小数 //例：$0.1234
- 0.0001 <= x < 0.1		6位小数 //例：$0.001234
- 0.00000001 <= x < 0.0001	8位小数 //例：$0.00001234
- x < 0.00000001		展示为0 //容错，暂时没有可能发生
- 没有数据时用"--"表示，有货币符号的数据不要带货币符号 //例：--
- 小数直接截断不4舍5入，小数点末位为0的不省略 //例：$54321.10
- 小数点全部为0的，隐藏 //例：$54321

<br><br>
#### 2. 非法币价格数据展示规则
- x >= 1000 0 0000		无小数 //容错，暂时没有可能发生
- 1000 <= x < 100000000  4位小数 //例：12345.1234 Ht
- 0.0001 <= x < 1000	6位小数 //例: 12.123456 Eth
- 0.000001<= x < 0.0001	8位小数 //例: ฿0.00001234
- 0.0000000001 <= x < 0.000001	10位小数 //例：฿0.0000001234
- x < 0.0000000001		展示为0 //容错，暂时没有可能发生
- 没有数据时用"--"表示，有货币符号的数据不要带货币符号 //例：--
- 小数直接截断不4舍5入，小数点末位为0的不省略 //例：0.0123400 Eth
- 小数点全部为0的，隐藏 //例：4321 Eth
- 币作为单位时，前面留一个空格 //例：1.2345 Eth
- 币作为单位时，第一位字母大写，后面都是小写 //例: 1.2345 Eth

<br><br>
#### 3. 涨跌幅百分比（+/-x%）数据展示规则
- x >= 10000            无小数点	//容错，小机率发生
- 0.01 <= x < 10000     保留2位小数
- x < 0.01              展示为0
- *没有数据时用"--"表示 //例：--
- *x=0时展示“0.00%”, 不展示"+/-"符号 //例：0.00%
- 小数直接截断不4舍5入，小数点末位为0的不省略 //例：12.10%
- 小数点全部为0的，展示 //例：12.00%
> *在旧版中，返回数据不能辨别x=0与没有数据，如果还是不能解决，按x=0处理，web端展示为"0%"，客户端为”0.00%“。

<br><br>
#### 4. 基数词头的适配规则
- 万    10<sup>4</sup> //默认>=10<sup>5</sup> 时适配，法币价格>=10<sup>6</sup> 时才适配
- 亿    10<sup>8</sup>
- 万亿  10<sup>12</sup> //国标；台，韩，日标准中称为“兆”
- 支持基数词头适配的字段：
流通市值 | 流通数量 | 成交额 | 总市值 | 法币价格
- 基数词头适配后，保留2位小数 //例：123.12万
- 小数直接截断不4舍5入，小数点末位为0的不省略 //例：123.10万
- 小数点全部为0的，隐藏 //例：123万

<br><br>
#### 5. 基数的三位逗号分隔符的适配规则
- *在简体中文语境下，没有适配基数词头的数字不采用三位逗号分隔。//例：54321； 5,432.12万
- 在简体中文语境下，非行情数据展示的数字不采用逗号分隔。//例：阅读数 54321;关注数 5432.12万
> *行情数据在其它非简体中文语境中，采用三位逗号分隔机制

<br><br>
#### 6. 陆，港，台，日，韩，英文的基数词头的规范
NUM | CN | HK | TW | JP | KR | EN
---|---|---|---|---|---|---
10<sup>4</sup> |1万 | 1萬 | 1萬 | 1万 | 1만 | 10K,1K=10<sup>3</sup>
10<sup>8</sup> |1亿 | 1億 | 1億 | 1億 | 1억 | 100M,1M=10<sup>6</sup>
10<sup>12</sup> |1万亿 | 1萬億 | 1兆 | 1兆 | 1조 | 1000B,1B=10<sup>9</sup>

<br><br>
#### 7. 货币选顶中的货币符号
代码 | 货币 | 符号 | 范例
---|---|---|---
xn5
> *除ETH,BTC外，其它适用法币展示规则

<br><br>
#### 8. 交易对中的其它法币符号
- ##### 约等于法币价值的稳定代币, 可以使用法币符号替代，适用法币数据展示规则
代码 | 货币 | 对应法币 | 可替代符号
---|---|---|---
BITCNY | 比特元 | 人民币 | ¥
CNYT | 未知 | 人民币 | ¥
CNH | 离岸人民币 | 人民币 | ¥
BITEUR |未知  | 欧元 | €
BITUSD | 未知 | 美元 | $
CK.USD | 未知 | 美元 | $
CKUSD | 未知 | 美元 | $
USDT | 泰达币 | 美元 | $
GUSD | 双子星美元 | 美元 | $
TUSD | 未知 | 美元 | $
USDC | 未知 | 美元 | $
PAX | 未知 | 美元 | $

- ##### 交易对中的其它法币符号，适用法币数据展示规则
代码 | 货币 | 符号 | 范例
---|---|---|---
CLP | 智利比索 | CLP | CLP100
COP | 哥伦比亚比索 | Col$ | Col$100
ILS | 以色列新谢克尔 | ₪ | ₪100
ISK | 冰岛克郎 | kr | kr100
MYR | 马元 | RM | RM100
NGN | 尼日利亚奈拉 | ₦ | ₦100
NZD | 新西兰元 | NZ$ | NZ$100
PEN | 秘鲁新索尔 | S/. | S/.100
PHP | 菲律宾比索 | ₱ | ₱100
PLN | 波兰兹罗提 | zł | zł100
SGD | 新加坡元 | SG$ | SG$100
THB | 泰铢* | ฿ | ฿100
TRY | 土耳其里拉 | ₺ | ₺100
UAH | 乌克兰格里夫纳 | ₴ | ₴100
VND | 越南盾 | ₫ | ₫100
ZAR | 南非兰特 | R | R100

> *比特币货币符号฿是借用泰铢法币符号，会与泰铢符号产生岐义，泰铁符号使用"T฿"来区分。

- 主价格为可切换的价格 (当前价格切换中的货币见“条目7”)
- 副价格为原价 (全球指数默认是USD，交易对为平台价)
- 当主，副价格为同一货币单位时，副价格展示为USD； 当同为USD时，副价格展示为CNY。(这条规则包含且不限于使用货币符号为单位的稳定代币)

<br><br>
#### 10. 交易所数据请求统一的国家代码
代码 | 国家 | EN | TW | HK | JP
---|---|---|---|---|---
cn | 中国 | China | 中國 | 中國 | 中国
uk | 英国 | United Kingdom | 英國 | 英國 | イギリス
us | 美国 | United States | 美國 | 美國 | アメリカ
jp | 日本 | Japan | 日本 | 日本 | 日本
kr | 韩国 | Korea | 韓國 | 韓國 | 韓国
hk | 中国香港 | China Hong Kong | 中國香港 | 中國香港 | 香港、中国
pl | 波兰 | Poland | 波蘭 | 波蘭 | ポーランド
ca | 加拿大 | Canada | 加拿大 | 加拿大 | カナダ
au | 澳洲 | Australia | 澳洲 | 澳洲 | オーストラリア
th | 泰国 | Thailand | 泰國 | 泰國 | タイ
in | 印度 | India | 印度 | 印度 | インド
de | 德国 | Germany | 德國 | 德國 | ドイツ
ua | 乌克兰 | Ukraine | 烏克蘭 | 烏克蘭 | ウクライナ
nz | 新西兰 | new Zealand | 紐西蘭 | 新西蘭 | ニュージーランド
ru | 俄罗斯 | Russia | 俄羅斯 | 俄羅斯 | ロシア
dk | 丹麦 | Denmark | 丹麥 | 丹麥 | デンマーク
sc | 塞舌尔 | Seychelles | 塞席爾 | 塞舌爾 | セイシェル
nl | 荷兰 | Netherlands | 荷蘭 | 荷蘭 | オランダ
es | 西班牙 | Spain | 西班牙 | 西班牙 | スペイン
mv | 马耳他 | Malta | 馬爾他 | 馬爾他 | マルタ
tz | 坦桑尼亚 | Tanzania | 坦尚尼亞 | 坦桑尼亞 | タンザニア
mx | 墨西哥 | Mexico | 墨西哥 | 墨西哥 | メキシコ
ch | 瑞士 | Switzerland | 瑞士 | 瑞士 | スイス
br | 巴西 | Brazil | 巴西 | 巴西 | ブラジル
tr | 土耳其 | Turkey | 土耳其 | 土耳其 | トルコ
il | 以色列 | Israel | 以色列 | 以色列 | イスラエル
tw | 中国台湾 | China Taiwan | 台灣 | 中國台灣 | 台湾、中国
hr | 克罗地亚 | Croatia | 克羅埃西亞 | 克羅地亞 | クロアチア
sg | 新加坡 | Singapore | 新加坡 | 新加坡 | シンガポール
cl | 智利 | Chile | 智利 | 智利 | チリ
ph | 菲律宾 | Philippines | 菲律賓 | 菲律賓 | フィリピン
za | 南非 | South Africa | 南非 | 南非 | 南アフリカ
mn | 蒙古国 | Mongolia | 蒙古 | 蒙古 | モンゴル
ee | 爱沙尼亚 | Estonia | 愛沙尼亞 | 愛沙尼亞 | エストニア
jo | 约旦 | Jordan | 約旦 | 約旦 | ヨルダン
it | 意大利 | Italy | 義大利 | 意大利 | イタリア
vn | 越南 | Vietnam | 越南 | 越南 | ベトナム
ae | 阿联酋 | United Arab Emirates | 阿聯 | 阿聯酋 | アラブ首長国連邦
ky | 开曼群岛 | Cayman Islands | 開曼群島 | 開曼群島 | ケイマン諸島
my | 马来西亚 | Malaysia | 馬來西亞 | 馬來西亞 | マレーシア
ws | 萨摩亚 | Samoa | 薩摩亞 | 薩摩亞 | サモア
unknown | 未知 | unknown | 未知 | 未知 | 不明
> 地域选项的命名一律不能称为“国家”，必须展示为“国家/地区”；<br>
台湾地区命名必须使用“中国台湾”（台湾正体译文除外）；<br>





