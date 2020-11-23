package com.goodbookclub.bookclub.services.greeting;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingAspect {

	@Pointcut("execution(* com.goodbookclub.bookclub.services.greeting.GreetingService.greet(..))")
	public void greet() {}
	
	
	@Before("greet()")
	public void beforeAdvice() {
		System.out.println("Before greet is executed.");
	}
	
	@After("greet()")
	public void afterAdvice() {
		System.out.println("After greet is executed.");
	}
	
	@AfterReturning(pointcut = "greet()", returning = "str")
	public void afterReturningAdvice(Object str) {
		System.out.println("After return from greet method execution: "+str.toString());
	}
	
	@AfterThrowing(pointcut = "greet()", throwing = "ex")
	public void afterThrowingAdvice(Exception ex) {
		System.out.println("Some error executed: "+ex.toString());
	}
}
