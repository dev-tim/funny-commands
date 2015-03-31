package info.devartem.funnycommands.lexer

import org.parboiled.errors.ParsingException
import org.scalatest._

import scala.util.Success


class CommandLexerTest extends FunSpec with Matchers with TryValues {

  describe("Command lexer") {
    it("should parse command with params") {
      val expectedResult = Success(LexedCommand("command", List(LexedParam("param1"), LexedParam("param2"))))
      CommandLexer.analyze("command param1 param2") should equal(expectedResult)
    }

    it("should parse uppercase command with uppercase arguments") {
      val expectedResult = Success(LexedCommand("COMMAND", List(LexedParam("PARAM"), LexedParam("anotherParam"))))
      CommandLexer.analyze("COMMAND PARAM anotherParam") should equal(expectedResult)
    }

    it("should parse command with no commands") {
      val expectedResult = Success(LexedCommand("command", List()))
      CommandLexer.analyze("command") should equal(expectedResult)
    }

    it("should parse command with non alphabetic arguments") {
      val expectedResult = Success(LexedCommand("command", List(LexedParam("1123"), LexedParam("%6q34"))))
      CommandLexer.analyze("command 1123 %6q34") should equal(expectedResult)
    }

    it("should parse command with uppercased arguments") {
      val expectedResult = Success(LexedCommand("command", List(LexedParam("PARAM"), LexedParam("anotherParam"))))
      CommandLexer.analyze("command PARAM anotherParam") should equal(expectedResult)
    }

    it("should throw error if wrong literals used for command") {
      CommandLexer.analyze("123%asdf PARAM anotherParam").failure.exception shouldBe a [ParsingException]
    }
  }
}
