package info.devartem.funnycommands.commands.operations

import info.devartem.funnycommands.exception.InvalidCommandException

trait Operation {
  def apply(): (String) => String

  val name: String
}


object NoopOperation extends Operation {

  override val name: String = "noop"

  override def apply() = {
    (s: String) => s
  }
}

object ReverseOperation extends Operation {

  override val name: String = "reverse"

  override def apply() = {
    (s: String) => s.reverse
  }
}

object DelayOperation extends Operation {

  override val name: String = "delay"

  var cache = "tbb"

  override def apply() = {
    (s: String) => {
      val res = cache
      cache = s
      res
    }
  }
}

object EchoOperation extends Operation {

  override val name: String = "echo"

  override def apply() = {
    (s: String) => s + s
  }
}

object CommandOperations {
  val availableOperations: Map[String, Operation] = List(NoopOperation, ReverseOperation, DelayOperation, EchoOperation)
    .foldLeft(Map[String, Operation]()) { (map, operation) => map.+(operation.name -> operation)}

  def operationByName(op: String): Operation = {
    availableOperations.getOrElse(op, throw InvalidCommandException(op))
  }
}
