package kr.mzhub.mzhub.service.iface

import kr.mzhub.mzhub.client.model.upbit.GetUpbitTicker.Ticker

data class TickerResponseDto(
    // 종목 구분 코드
    // KRW-BTC"
    val market: String,

    // 현재가(종가)
    // 8621000,
    val trade_price: String,

    // 전일 종가 (UTC 0시 기준)
    // 8450000,
    val prev_closing_price: String,

    // 변화방향
    // "RISE",
    val change: String,

    // 부호가 있는 변화량
    // 171000,
    val signed_change_price: String,

    // 부호가 있는 변화율
    // 0.0202366864,
    val signed_change_rate: String,

    // 전일 누적 거래량(UTC 0시 기준)
    // 12603.53386105,
    val acc_trade_volume: String,

    // 24시간 누적 거래량
    // 27181.31137002,
    val acc_trade_volume_24h: String,
) {
    companion object {
        fun fromUpbit(ticker: Ticker): TickerResponseDto {
            return TickerResponseDto(
                ticker.market,
                ticker.trade_price,
                ticker.prev_closing_price,
                ticker.change,
                ticker.signed_change_price,
                ticker.signed_change_rate,
                ticker.acc_trade_volume,
                ticker.acc_trade_volume_24h
            )
        }
    }
}
