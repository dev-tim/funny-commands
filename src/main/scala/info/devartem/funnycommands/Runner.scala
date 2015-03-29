package info.devartem.funnycommands

import info.devartem.funnycommands.commands.Command
import info.devartem.funnycommands.lexer.CommandLexer


object Runner {


  def main(args: Array[String]) {
    CommandsContext
    Iterator
      .continually(Console.readLine())
      .takeWhile((line: String) => line != null && line != "quit")
      .foreach(line => {
      val boundCommand = for {
        lx <- CommandLexer.analyze(line)
        command <- Command.commandByName(lx.name)
        boundCommand <- command.bind(lx.args)
      } yield boundCommand


    })
  }


}


