package info.devartem.funnycommands.commands

import info.devartem.funnycommands.CommandsContext
import info.devartem.funnycommands.exception.CommandValidationException
import info.devartem.funnycommands.lexer.LexedParam

import scala.util.Try

case class Process(name: String = "process", result: List[String] = List())
                  (implicit ctx: CommandsContext) extends Command {

  override def bind(params: List[LexedParam]): Try[Command] = {
    Try(params match {
      case list if list.size > 0 =>
        val executionRes: List[String] = ctx.executeTasks(list.map(_.name))
        this.copy(result = executionRes)
      case _ => throw new CommandValidationException(s"Not enough arguments for $name command")
    })
  }
}
