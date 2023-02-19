package kr.mzhub.mzhub.controller

import kr.mzhub.mzhub.service.BinanceService
import kr.mzhub.mzhub.service.iface.BinanceTickerResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/binance")
class BinanceController(
    private val binanceService: BinanceService,
) {
    @GetMapping("/ticker/{symbols}")
    fun getTicker(@PathVariable symbols: String): Mono<BinanceTickerResponseDto> {
        return binanceService.getTicker("[\"$symbols\"]")
    }
}
