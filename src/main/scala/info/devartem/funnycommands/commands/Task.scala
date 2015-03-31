package info.devartem.funnycommands.commands

import info.devartem.funnycommands.CommandsContext
import info.devartem.funnycommands.commands.operations.CommandOperations
import info.devartem.funnycommands.exception.CommandValidationException
import info.devartem.funnycommands.lexer.LexedParam

import scala.util.Try

case class Task(name: String = "task")(implicit ctx: CommandsContext) extends Command {

  override def bind(params: List[LexedParam]): Try[Command] = {
    Try(params match {
      case n :: op :: xs if isOperationDefined(op.name) =>
        ctx.defineTask(n.name, getOperation(op.name).apply())
        this
      case n :: op :: xs if !isOperationDefined(op.name) =>
        throw new CommandValidationException(s"No such operation defined ${op.name} for $name command")
      case _ =>
        throw new CommandValidationException(s"Not enough arguments for $name command")
    })
  }

  def isOperationDefined(operation: String): Boolean = {
    CommandOperations.availableOperations.isDefinedAt(operation)
  }

  def getOperation(operation: String) = {
    CommandOperations.operationByName(operation)
  }
}
