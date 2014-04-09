package com.tennissetapp.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class TXAspect {
	Logger logger = Logger.getLogger(getClass());

	public TXAspect() {
		// System.out.println("---->TXAspect constructor");
	}

	// @Pointcut("execution(* com.tennissetapp.rest.MatchesService.search(..))")

	@AfterReturning(pointcut = "execution(* com.tennissetapp.rest.MatchesService.search(..))", returning = "result")
	protected void createOperation() {
		System.out.println("--->TXAspect.search MATCHES");
	}

	
	@AfterThrowing(pointcut = "execution(* com.tennissetapp.rest.MatchesService.join(..))", throwing = "error")
	public void handleDuplicateRecordException(JoinPoint joinPoint,Throwable error) {
		logger.info("handleDuplicateRecordException: " + joinPoint + ", "
				+ error);

		// retrieve the methods parameter types (static):
		final Signature signature = joinPoint.getStaticPart().getSignature();
		if (signature instanceof MethodSignature) {
			final MethodSignature ms = (MethodSignature) signature;
			final Class<?>[] parameterTypes = ms.getParameterTypes();
			for (final Class<?> pt : parameterTypes) {
				System.out.println("Parameter type:" + pt);
			}
		}

		// retrieve the runtime method arguments (dynamic)
		for (final Object argument : joinPoint.getArgs()) {
			System.out.println("Parameter value:" + argument);
		}

	}
}
