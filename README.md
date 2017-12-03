# Spring-AOP
Demo project for Spring-AOP


## What is AOP?
Aspect Oriented Programming addresses the problem of cross-cutting concerns which would be any kind of code that is repeated in your application
the application and cannot normally be completely refactored into its own module, like logging, error handling, and transaction. 

###AOP Terminology
* Aspect: The class that contains advice and pointcut. (ex. ControllerAspect and ServiceAspect are the aspects.)
* Pointcut: Defines which methods the advice is to be weaved with.(ex. logging in the ServiceAspect)
* Advice: The method to be run when program execution reaches the joint points in the pointcut. (ex. methods in the Aspect other than pointcut)
