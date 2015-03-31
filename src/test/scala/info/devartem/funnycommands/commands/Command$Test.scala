package info.devartem.funnycommands.commands

import info.devartem.funnycommands.exception.InvalidCommandException
import org.scalatest.{FunSpec, Matchers, TryValues}

class Command$Test extends FunSpec with Matchers with TryValues {

  describe("Command object") {
    it("should return task command by name") {
      Command.commandByName("task").success.value shouldBe a[Task]
    }

    it("should return link command by name") {
      Command.commandByName("link").success.value shouldBe a[Link]
    }

    it("should return process command by name") {
      Command.commandByName("process").success.value shouldBe a[Process]
    }

    it("should return failed try in command is not found") {
      Command.commandByName("dummyCommand").failure.exception shouldBe a[InvalidCommandException]
    }
  }
}
