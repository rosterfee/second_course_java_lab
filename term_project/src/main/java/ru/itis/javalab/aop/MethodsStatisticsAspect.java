package ru.itis.javalab.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.redis.RedisService;

@Component
@Aspect
public class MethodsStatisticsAspect {

    @Autowired
    private RedisService redisService;

    @Pointcut("execution(public * ru.itis.javalab.services..*(..))")
    public void allServicesPointcut(){}

    @Around("allServicesPointcut()")
    public Object addStatistics(ProceedingJoinPoint joinPoint) {

        StringBuilder method = new StringBuilder("public ");

        method.append(joinPoint.getTarget().getClass().getName()).append(" ");
        method.append(joinPoint.getSignature().getName()).append(" ");

        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            method.append("();");
        } else {

            Class<?> firstArgumentClass = args[0].getClass();
            String firstArgumentClassName = firstArgumentClass.getName();
            String firstArgumentLoweCaseSimpleClassName = firstArgumentClass
                    .getSimpleName().toLowerCase();

            method
                    .append("(")
                    .append(firstArgumentClassName)
                    .append(" ")
                    .append(firstArgumentLoweCaseSimpleClassName);

            Class<?> argumentClass;
            String argumentClassName;
            String argumentLoweCaseSimpleClassName;
            for (int i = 1; i < args.length; i++) {
                argumentClass = args[i].getClass();
                argumentClassName = argumentClass.getName();
                argumentLoweCaseSimpleClassName = argumentClass.getSimpleName().toLowerCase();
                method
                        .append(", ")
                        .append(argumentClassName)
                        .append(" ")
                        .append(argumentLoweCaseSimpleClassName);
            }
            method.append(");");
        }

        String methodRecord = method.toString();

        if (redisService.hasKey(methodRecord)) {
            int count = Integer.parseInt(redisService.getElementByKey(methodRecord));
            count++;
            redisService.set(methodRecord, String.valueOf(count));
        }
        else {
            redisService.set(methodRecord, "1");
        }

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return null;
        }

    }

}
