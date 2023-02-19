package kr.mzhub.mzhub.client.model.binance

data class GetBinanceTicker(
    val tickers: List<Ticker>
) {

    data class Ticker(
        val symbol: String,
        val price: String,
    )
}
