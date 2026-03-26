package com.example.e_comm

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ECommApplicationTests {
    //
//	@Test
//	fun contextLoads() {
//	}
    @Test
    fun testAddition() {
        assertEquals(4, 2 + 2)
    }

}
