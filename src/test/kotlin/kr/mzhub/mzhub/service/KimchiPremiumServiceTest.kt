package kr.mzhub.mzhub.service

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KimchiPremiumServiceTest {

    @Autowired
    lateinit var kimchiPremiumService: KimchiPremiumService

    @Test
    fun getKimchiPremium() {
        val result = runBlocking {
            kimchiPremiumService.getKimchiPremium("KRW-BTC")
        }
        println(result)
    }
}