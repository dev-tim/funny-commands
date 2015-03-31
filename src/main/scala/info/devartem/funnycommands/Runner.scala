package info.devartem.funnycommands

import info.devartem.funnycommands.commands._
import info.devartem.funnycommands.lexer._


object Runner {

  def main(args: Array[String]) {
    Iterator
      .continually(Console.readLine())
      .takeWhile((line: String) => line != null && line != "quit")
      .foreach(line => {
      val commands = for {
        lx <- CommandLexer.analyze(line)
        command <- Command.commandByName(lx.name)
        boundCommand <- command.bind(lx.args)
      } yield boundCommand

      commands map {
        case Process(name, result) =>
          println(s"Commands execution result:\n\n${result.mkString(" ")}")
        case _ =>
          println("Please define another task or type process ...")
      } recover {
        case e => println("Error!!", e.getMessage)
      }
    })
  }


}


