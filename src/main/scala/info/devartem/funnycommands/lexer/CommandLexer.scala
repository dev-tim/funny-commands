package info.devartem.funnycommands.lexer

import org.parboiled.errors.{ErrorUtils, ParsingException}
import org.parboiled.scala._
import org.parboiled.scala.parserunners.ReportingParseRunner
import org.parboiled.scala.rules.Rule1

import scala.util.Try


/**
 * Defined rules for lexer
 */
object CommandLexer extends Parser {

  private def Expression: Rule1[LexedCommand] = rule {
    WhiteSpace ~ CommandExpr ~ WhiteSpace ~ EOI
  }

  private def CommandExpr: Rule1[LexedCommand] = rule {
    CommandName ~ WhiteSpace ~ CommandParam ~~> LexedCommand
  }

  private def CommandName: Rule1[String] = rule {
    oneOrMore(Symbols) ~> (name => name.trim)
  }

  private def CommandParam: Rule1[List[LexedParam]] = {
    zeroOrMore(CommandParamValue, separator = WhiteSpace) ~~> (params => params)
  }

  private def CommandParamValue: Rule1[LexedParam] = {
    oneOrMore(CharsWithoutSpaces) ~> (value => LexedParam(value)) ~~> (data => data)
  }

  private def CharsWithoutSpaces = rule {
    !anyOf(" ") ~ ANY
  }

  private def WhiteSpace: Rule0 = rule {
    zeroOrMore(anyOf(" \n\r\t\f"))
  }

  private def Symbols = rule {
    "a" - "z" | "A" - "Z"
  }

  /**
   * Parse string and transform it into sequence of Command objects
   * @param expression - expression to parse
   * @return list of DslCommand's
   */
  def analyze(expression: String): Try[LexedCommand] = {
    val parsingResult: ParsingResult[LexedCommand] = ReportingParseRunner(Expression).run(expression)
    Try(parsingResult.result
      .getOrElse(throw new ParsingException(s"Invalid calculation expression:\n${ErrorUtils.printParseErrors(parsingResult)}"))
    )
  }
}
