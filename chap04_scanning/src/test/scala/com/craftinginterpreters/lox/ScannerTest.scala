package com.craftinginterpreters.lox

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

import java.util.List

class ScalaScannerTest {

    @Disabled("Not implemented yet")
    @Test
    def notImplemented() {}

    @Test
    def onePlusThree() {
	System.out.println("ScannerTester#onePlusThree() executed");

	val scanner = new Scanner("1 + 3;");
	val tokens = scanner.scanTokens();
	println(tokens);
    //     assertEquals(tokens.get(0).type(), NUMBER);
    //     assertEquals(tokens.get(2).type(), PLUS);
    //     assertEquals(tokens.get(3).type(), NUMBER);
    //     assertEquals(tokens.get(4).type(), SEMICOLON);
    //     assertEquals(tokens.get(5).type(), SEMICOLON);
    }

}
