package kr.mzhub.mzhub.client

import com.fasterxml.jackson.databind.ObjectMapper
import kr.mzhub.mzhub.client.model.Ticker
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.stereotype.Component
import org.springframework.util.MimeType
import org.springframework.util.MimeTypeUtils
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class UpbitClient(
    webClientBuilder: WebClient.Builder,
){
    private val upbitUrl = "https://api.upbit.com/v1/ticker"
    private val backendWebClient = webClientBuilder
        .baseUrl(upbitUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .logging()
        .exchangeStrategies(
            ExchangeStrategies.builder()
                .codecs {
                    it.defaultCodecs().jackson2JsonDecoder(
                        Jackson2JsonDecoder(
                            ObjectMapper(),
                            MimeType.valueOf(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        )
                    )
                }.build()
        )
        .filter(
            ExchangeFilterFunction.ofResponseProcessor { response ->
                    Mono.just(response)
            }
        )
        .build()

    fun getTicker(markets: String): Mono<Array<Ticker>> {
        return backendWebClient
            .get()
            .uri {
                it.queryParam("markets", markets).build()
            }
            .retrieve()
            .bodyToMono(Array<Ticker>::class.java)
    }

}