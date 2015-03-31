# funny-commands

Hi folks! 

Here is an exmaple of command execution framework, where you I have defined my own "micro langulage".
Basic idea here is to define tasks in the context and build pipeline of execution for there tasks.

For the simplification I took the idea that every operation can represent different kind of action, in this example we have just 3 
main operations: 
+ *Task* - define a binding to some operation in format ```task <name> <operation>``` where operation is a predefined function that 
manipulates with strings. Currently are supported 4 operations: *noop*, *delay*, *reverse*, *echo*.
+ *Link* - define a pipe from one task to another. It modifies task in pipline execution and do a combination of a functions
(operations bound to defined tasks). 
+ *Process* - execues pipeline and returns a result a a list of strings.

