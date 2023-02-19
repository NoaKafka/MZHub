package kr.mzhub.mzhub.service

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service

@Service
class KimchiPremiumService(
    private val binanceService: BinanceService,
    private val upbitService: UpbitService,
) {

    suspend fun getKimchiPremium(symbol: String): String {
        val symbols = symbol.split("-")
        val fotBinance = "[\"${symbols[1] + "USDT"}\"]"

        val binanceValue = binanceService.getTicker(fotBinance).awaitSingle()
        val upbitValue = upbitService.getTicker(symbol).awaitSingle()
        val exchangeRate =1300.0

        return calculatePremuim(binanceValue.tickers[0].price.toDouble() * exchangeRate, upbitValue.trade_price.toDouble())

    }
    fun calculatePremuim(binance: Double, upbit: Double): String {
        return (((upbit - binance) / upbit) * 100).toString()
    }
}