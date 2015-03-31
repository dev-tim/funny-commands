package info.devartem.funnycommands.commands

import info.devartem.funnycommands.CommandsContext
import info.devartem.funnycommands.exception._
import info.devartem.funnycommands.lexer._

import scala.util.Try

case class Link(name: String = "link")(implicit ctx: CommandsContext) extends Command {

  override def bind(params: List[LexedParam]) = {
    Try(params match {
      case t1 :: t2 :: xs if ctx.isTasksDefined(t1.name, t2.name) => {
        ctx.linkTasks(t1.name, t2.name)
        this
      }
      case _ => throw new CommandValidationException(s"Not enough arguments for $name command")
    })
  }
}
