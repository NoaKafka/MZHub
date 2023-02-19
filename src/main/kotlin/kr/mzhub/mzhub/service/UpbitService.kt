package kr.mzhub.mzhub.service

import kr.mzhub.mzhub.client.UpbitClient
import kr.mzhub.mzhub.client.model.upbit.GetUpbitTicker
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UpbitService(
    private val upbitClient: UpbitClient
) {
    fun getTicker(markets: String): Mono<GetUpbitTicker.Ticker> {
        return upbitClient.getTicker(markets).map {
            it[0]
        }
    }
}
