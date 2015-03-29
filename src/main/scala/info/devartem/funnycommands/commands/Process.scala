package info.devartem.funnycommands.commands

import info.devartem.funnycommands.lexer.LexedParam

import scala.util.{Failure, Try}

class Process extends Command{
  override val name: String = "process"

  override def bind(params: List[LexedParam]): Try[Command] = {
    Failure(new Exception)
  }
}
