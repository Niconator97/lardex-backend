package io.github.niconator97.lardexbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LardexBackendApplication

fun main(args: Array<String>) {
    runApplication<LardexBackendApplication>(*args)
}
