package info.devartem.funnycommands.commands.operations

import info.devartem.funnycommands.exception.InvalidCommandException
import org.scalatest._

class CommandOperations$Test extends FunSpec with Matchers with TryValues {

  describe("Command operations object") {
    it("should return echo operation by name") {
      CommandOperations.operationByName("echo") should be (EchoOperation)
    }

    it("should return delay operation by name") {
      CommandOperations.operationByName("delay") should be (DelayOperation)
    }

    it("should return reverse operation by name") {
      CommandOperations.operationByName("reverse") should be (ReverseOperation)
    }

    it("should return noop operation by name") {
      CommandOperations.operationByName("noop") should be (NoopOperation)
    }

    it("should return failed try is not found") {
      intercept[InvalidCommandException] {
        CommandOperations.operationByName("dummyOpenration")
      }
    }
  }


}
