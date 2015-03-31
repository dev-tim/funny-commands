# funny-commands

Hi folks!

Here is an example of command execution framework, where you I have defined my own "micro language". Basic idea here is to define tasks in the context and build a pipeline of execution for their tasks. For that we define rules to build simple AST with a help of [Paraboled](https://github.com/sirthias/parboiled/wiki). 

For the simplification I took the idea that every operation can represent different kind of action, in this example we have just 3 main operations:

+ *Task* - define a binding to some operation in format ```task <name> <operation>``` where an operation is a predefined function that manipulates with strings.
Currently are supported 4 operations: ```noop```, ```delay```, ```reverse```, ```echo```. The First task is added to an execution pipeline.
+ *Link* - define a pipe from one task to another. It modifies task in pipeline execution and do a combination of functions (operations bound to defined tasks).
+ *Process* - executes pipeline and returns a result a list of strings.


Sample input output you can see here:

```

task t1 echo

Defined task: t1
Please define another task or type process ...
process abc joe
Cleaned pipeline
Commands execution result:

abcabc joejoe

```


Let's take a look at more complicated case: 

```
task t1 echo 
Defined task: t1
Please define another task or type process ...
task t2 reverse
Defined task: t2
Please define another task or type process ...
task t3 echo
Defined task: t3
Please define another task or type process ...
link t1 t2
Lined tasks: t1 => t2
Please define another task or type process ...
link t2 t3
Lined tasks: t2 => t3
Please define another task or type process ...
process abc bimbo grasp 
Cleaned pipeline
Commands execution result:

cbacba obmibobmib psargpsarg

```

