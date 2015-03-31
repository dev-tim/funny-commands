package info.devartem.funnycommands.commands

import info.devartem.funnycommands.CommandsContext
import info.devartem.funnycommands.exception.InvalidCommandException
import info.devartem.funnycommands.lexer.LexedParam

import scala.util.Try

/**
 * Abstract class for Commands in DSL.
 * There we define all common properties for all the commands
 */
abstract class Command(implicit ctx : CommandsContext) {

  /**
   * Name of the command
   */
  val name: String

  /**
   * Binds command with lexed params to command context
   * @param params lexed params
   * @return instance of the command
   */
  def bind(params: List[LexedParam]): Try[Command]
}

object Command {

  implicit val ctx = CommandsContext()

  val availableCommands: Map[String, Command] = List(new Link, new Task, new Process)
    .foldLeft(Map[String, Command]()) { (map, command) => map.+(command.name -> command)}

  def commandByName(commandName: String): Try[Command] = {
    Try(availableCommands.getOrElse(commandName, throw InvalidCommandException(commandName)))
  }
}