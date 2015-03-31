package info.devartem.funnycommands

class CommandsContext {

  /**
   * Here we use state as a workaround,
   * we could use actors or some storage to keep the state
   * and ideally there should be NO state.
   */
  private var pipelineList: List[(String, (String) => String)] = List()

  private var definedTasksFns: Map[String, (String) => String] = Map()

  def pipeline() = pipelineList

  def linkTasks(fromTask: String, toTask: String): List[(String, (String) => String)] = {
    definedTasksFns.get(toTask) match {
      case Some(toTaskFn) =>
        pipelineList = pipelineList map {
          case (name, f) if name == fromTask =>
            (name, f.andThen(toTaskFn))
          case (name, f) =>
            (name, f)
        }
        println(s"Lined tasks: $fromTask => $toTask")
      case None => ()
    }
    pipelineList
  }

  def defineTask(taskName: String, op: (String) => String): Map[String, (String) => String] = {
    // init pipe line with first task due to req (not so good side effect)
    if (pipelineList.isEmpty) {
      pipelineList = pipelineList ::: List(taskName -> op)
    }
    definedTasksFns = definedTasksFns.+(taskName -> op)

    pipelineList = pipelineList.map {
      case (n, f) if taskName == n => (taskName, op)
      case (n, f) => (n, f)
    }

    println(s"Defined task: $taskName")
    definedTasksFns
  }

  def executeTasks(params: List[String]): List[String] = {
    val res = params.map(p => {
      pipelineList.foldLeft(p) {
        case (r, (name, fn)) => {
          fn(r)
        }
      }
    })
    pipelineList = List()
    println("Cleaned pipeline")
    res
  }

  def isTasksDefined(tasks: String*): Boolean = {
    tasks.forall(definedTasksFns.contains)
  }

}

object CommandsContext {
  def apply() = {
    new CommandsContext
  }
}
