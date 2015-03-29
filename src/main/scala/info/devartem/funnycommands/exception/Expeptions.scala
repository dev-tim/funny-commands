package info.devartem.funnycommands.exception


case class CommandValidationException(commandName: String, cause: String) extends RuntimeException

case class InvalidCommandException(commandName: String) extends RuntimeException

