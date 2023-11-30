package com.craftinginterpreters.lox;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import static com.craftinginterpreters.lox.TokenType.*;

class ScannerTest {


    @Disabled("Not implemented yet")
    @Test
    void testNotImplemented() {}

    @Test
    void onePlusThree() {
  System.out.println("ScannerTester#onePlusThree() executed");

  Scanner scanner = new Scanner("1 + 3;");
  List<Token> tokens = scanner.scanTokens();
  System.out.println(tokens);
        assertEquals(tokens.get(0).type(), NUMBER);
        assertEquals(tokens.get(2).type(), PLUS);
        assertEquals(tokens.get(3).type(), NUMBER);
        assertEquals(tokens.get(4).type(), SEMICOLON);
        assertEquals(tokens.get(5).type(), SEMICOLON);
    }

}
