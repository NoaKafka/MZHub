package kr.mzhub.mzhub.service.iface

import kr.mzhub.mzhub.client.model.binance.GetBinanceTicker

data class BinanceTickerResponseDto(
    val tickers: List<BinanceTickerDto>
) {

    companion object {
        fun of(tickerList: List<GetBinanceTicker.Ticker>): BinanceTickerResponseDto {
            return BinanceTickerResponseDto(
                tickers = tickerList.map {
                    BinanceTickerDto.fromBinance(it)
                }
            )
        }
    }

    class BinanceTickerDto(
        val symbol: String,
        val price: String,
    ) {
        companion object {
            fun fromBinance(ticker: GetBinanceTicker.Ticker): BinanceTickerDto {
                return BinanceTickerDto(
                    symbol = ticker.symbol,
                    price = ticker.price
                )
            }
        }
    }
}
