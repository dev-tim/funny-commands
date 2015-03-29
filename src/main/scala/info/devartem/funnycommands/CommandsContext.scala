package info.devartem.funnycommands

object CommandsContext {

  /**
   * Here we use state as a workaround,
   * we could use actors or some storage to keep the state
   * and ideally there should be NO state.
   */
  private var pipelineList: List[(String, (String) => String)] = List()

  private var definedTasksFns: Map[String, (String) => String] = Map()

  def pipeline() = pipelineList

  def linkTasks(fromTask: String, toTask: String) = {
    val toTaskFn = definedTasksFns(toTask)
    pipelineList = pipelineList map {
      case (name, f) if name == fromTask =>
        (name, f.andThen(toTaskFn))
      case (name, f) =>
        (name, f)
    }

   // list.map(a => a._2("abc")).mkString(" ")
  }

  def defineTask(name: String, op: (String) => String) = {
    // init pipe line with first task due to req (not so good side effect)
    if (pipelineList.isEmpty) {
      pipelineList = pipelineList ::: List(name -> op)
    }

    definedTasksFns = definedTasksFns.+(name -> op)
  }

  def isTasksDefined(tasks: String*): Boolean = {
    tasks.forall(CommandsContext.definedTasksFns.contains)
  }

}

