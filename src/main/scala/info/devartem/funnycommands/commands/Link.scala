package info.devartem.funnycommands.commands

import info.devartem.funnycommands.CommandsContext
import info.devartem.funnycommands.exception.CommandValidationException
import info.devartem.funnycommands.lexer.LexedParam

import scala.util.Try

class Link extends Command {
  override val name: String = "link"

  override def bind(params: List[LexedParam]) = {
    Try(params match {
      case t1 :: t2 :: xs if CommandsContext.isTasksDefined(t1.name, t2.name) => {
        CommandsContext.linkTasks(t1.name, t2.name)
        this
      }
      case _ => throw CommandValidationException(name, "Not enough arguments")
    })
  }
}
