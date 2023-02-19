package kr.mzhub.mzhub.client.model

class GetTickerResult(
    val tickers: Array<Ticker>
)

class Ticker(
    val market: String, //KRW-BTC"
    val trade_date: String,   //"20180418",
    val trade_time: String,    //"102340",
    val trade_date_kst: String, //"20180418",
    val trade_time_kst: String, //"192340",
    val trade_timestamp: String, //1524047020000,
    val opening_price: String, //8450000,
    val high_price: String, //8679000,
    val low_price: String, //8445000,
    val trade_price: String, //8621000,
    val prev_closing_price: String, //8450000,
    val change: String, //"RISE",
    val change_price: String, //171000,
    val change_rate: String, //0.0202366864,
    val signed_change_price: String, //171000,
    val signed_change_rate: String, //0.0202366864,
    val trade_volume: String, //0.02467802,
    val acc_trade_price: String, //108024804862.58253,
    val acc_trade_price_24h: String, //232702901371.09308,
    val acc_trade_volume: String, //12603.53386105,
    val acc_trade_volume_24h: String, //27181.31137002,
    val highest_52_week_price: String, //28885000,
    val highest_52_week_date: String, //"2018-01-06",
    val lowest_52_week_price: String, //4175000,
    val lowest_52_week_date: String, //"2017-09-25",
    val timestamp: String, //1524047026072
)