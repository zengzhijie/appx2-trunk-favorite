package com.dreawer.favorite.aop;

import com.dreawer.responsecode.rcdt.Error;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.favorite.exception.ResponseCodeException;
import com.dreawer.favorite.utils.JsonFormatUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <CODE>ControllerAOP</CODE>
 *
 * @author fenrir
 * @Date 18-8-6
 */


@Aspect
@Component
public class ControllerAOP {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    @Pointcut("execution(com.dreawer.responsecode.rcdt.ResponseCode com.dreawer.favorite.controller.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public ResponseCode hanlderControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ResponseCode result;
        try {
            result = (ResponseCode) pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        logger.info(JsonFormatUtil.formatJson(result));
        return result;
    }

    private ResponseCode handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResponseCode result;
        // 已知异常
        if (e instanceof ResponseCodeException) {
            logger.error("其他系统异常", e);
            result = ((ResponseCodeException) e).getResponseCode();
        } else {
            logger.error(pjp.getSignature() + " error ", e);
            result = Error.APPSERVER;
        }

        return result;
    }
}
