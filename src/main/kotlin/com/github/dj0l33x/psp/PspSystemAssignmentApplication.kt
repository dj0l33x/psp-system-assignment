package com.github.dj0l33x.psp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry

@EnableRetry
@SpringBootApplication
class PspSystemAssignmentApplication

fun main(args: Array<String>) {
    runApplication<PspSystemAssignmentApplication>(*args)
}
