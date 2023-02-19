package kr.mzhub.mzhub.client

import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientRequestException
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import java.time.Duration

private val webClientExtensionLogger = LoggerFactory.getLogger("WebClientExtension Logger")!!

fun WebClient.Builder.requestLoggingFilter(): WebClient.Builder {
    return this.filter(
        ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
            clientRequest.headers().forEach { name, values ->
                values.forEach { value ->
                    webClientExtensionLogger.info("Request Header: {}: {}", name, value)
                }
            }
            webClientExtensionLogger.info("Request: {} {}", clientRequest.method(), clientRequest.url())
            Mono.just(clientRequest)
        }
    )
}

fun WebClient.Builder.responseLoggingFilter(): WebClient.Builder {
    return this.filter(
        ExchangeFilterFunction.ofResponseProcessor { clientResponse ->
            clientResponse.headers().asHttpHeaders().forEach { name, values ->
                values.forEach { value ->
                    webClientExtensionLogger.info("Response Header: {}: {}", name, value)
                }
            }
            Mono.create {
                clientResponse.bodyToMono(String::class.java).subscribe(
                    { responseBody ->
                        webClientExtensionLogger.info("Response Body: {}", responseBody)
                        it.success(
                            ClientResponse.create(clientResponse.statusCode())
                                .headers { it.addAll(clientResponse.headers().asHttpHeaders()) }
                                .body(responseBody)
                                .build()
                        )
                    },
                    { throwable ->
                        webClientExtensionLogger.error("Response Body: {}", throwable.message)
                        it.error(throwable)
                    }
                )
            }
        }
    )
}

fun WebClient.Builder.logging(): WebClient.Builder {
    return this.requestLoggingFilter().responseLoggingFilter()
}

fun <T> Mono<T>.retryWhenUnprocessedRequest(): Mono<T> {
    return this.retryWhen(
        Retry
            .backoff(3, Duration.ofMillis(100))
            .filter { it is WebClientRequestException }
    )
}