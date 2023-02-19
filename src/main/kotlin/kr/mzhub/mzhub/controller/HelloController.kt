package kr.mzhub.mzhub.controller

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
@Slf4j
class HelloController {

    private val logger = LoggerFactory.getLogger(HelloController::class.java)

    @GetMapping("/{id}")
    fun hello(@PathVariable id: String):  ResponseEntity<String>{
        logger.info("$id log in")
        return ResponseEntity.ok("Hello, $id")
    }
}
