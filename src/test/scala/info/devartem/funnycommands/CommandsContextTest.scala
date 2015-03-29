package info.devartem.funnycommands

import org.scalatest._

class CommandsContextTest extends FunSpec with Matchers {

  describe("CommandsContext") {
    it("should update state of varaible") {
      CommandsContext.pipelineFlow.+("Ololo")
      CommandsContext.pipelineFlow.size should be > 1

    }
  }

}
