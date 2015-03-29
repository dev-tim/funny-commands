package info.devartem.funnycommands.lexer

/**
 * Representation of lexed result
 */
case class LexedCommand(name: String, args: List[LexedParam])

case class LexedParam(name: String)
