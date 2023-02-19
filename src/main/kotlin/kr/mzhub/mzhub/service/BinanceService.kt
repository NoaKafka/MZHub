package kr.mzhub.mzhub.service

import kr.mzhub.mzhub.client.BinanceClient
import kr.mzhub.mzhub.service.iface.BinanceTickerResponseDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BinanceService(
    private val binanceClient: BinanceClient,
) {
    fun getTicker(symbols: String): Mono<BinanceTickerResponseDto> {
        return binanceClient.getTicker(symbols).map {
            BinanceTickerResponseDto.of(it.toList())
        }
    }
}
