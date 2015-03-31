package info.devartem.funnycommands

import org.scalatest.{FunSpec, Matchers}

class CommandsContextTest extends FunSpec with Matchers {

  describe("CommandsContext") {

    it("should have no defined tasks in the beginning ") {
      val ctx = CommandsContext()
      ctx.isTasksDefined("test") should be(false)
    }

    it("should define task to context") {
      val ctx = CommandsContext()
      ctx.defineTask("test", (a) => a.reverse)
      ctx.isTasksDefined("test") should be(true)
    }

    it("should override defined task by another") {
      val ctx = CommandsContext()
      ctx.defineTask("test", (a) => a)
      ctx.defineTask("test", (a) => a.reverse)

      ctx.executeTasks(List("abc")) should be(List("cba"))
    }

    it("should put first defined task to context") {
      val ctx = CommandsContext()
      ctx.defineTask("test", (a) => a)
      ctx.defineTask("anotherTask", (a) => a.reverse)

      ctx.pipeline().size should be(1)
    }

    it("should link defined tasks") {
      val ctx = CommandsContext()
      ctx.defineTask("t1", (s) => s + s)
      ctx.defineTask("t2", (s) => s.reverse)
      ctx.linkTasks("t1", "t2")

      ctx.executeTasks(List("abc")) should be(List("cbacba"))
    }

    it("should not link undefined tasks") {
      val ctx = CommandsContext()
      ctx.linkTasks("undefinedFirst", "undefinedSecond")
      ctx.executeTasks(List("abc")) should be(List("abc"))
    }

    it("should execute tasks from pipeline") {
      val ctx = CommandsContext()
      ctx.defineTask("someTask", (s) => s + s)
      ctx.executeTasks(List("abc")) should be(List("abcabc"))
    }

    it("should execute tasks from pipeline for multiply arguments") {
      val ctx = CommandsContext()
      ctx.defineTask("someTask", (s) => s + s)
      ctx.executeTasks(List("abc", "joe")) should be(List("abcabc", "joejoe"))
    }

    it("should clear pipeline after executing tasks") {
      val ctx = CommandsContext()
      ctx.defineTask("someTask", (s) => s + s)
      ctx.executeTasks(List("abc", "joe")) should be(List("abcabc", "joejoe"))
      ctx.executeTasks(List("abc", "joe")) should be(List("abc", "joe"))
    }
  }

}
