package kr.mzhub.mzhub.controller

import kr.mzhub.mzhub.service.UpbitService
import kr.mzhub.mzhub.service.iface.TickerResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/upbit")
class UpbitController(
    private val upbitService: UpbitService
) {
    @GetMapping("/ticker/{markets}")
    fun getTicker(@PathVariable markets: String): Mono<TickerResponseDto> {
        return upbitService.getTicker(markets).map {
            TickerResponseDto.fromUpbit(it)
        }
    }
}
