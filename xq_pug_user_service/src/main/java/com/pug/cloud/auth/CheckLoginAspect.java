package com.pug.cloud.auth;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.pug.cloud.service.JwtOperatorService;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CheckLoginAspect {

    @Autowired
    private JwtOperatorService jwtOperatorService;

    @Pointcut("@annotation(com.pug.cloud.auth.CheckLogin)")
    public void pointcut() {
    }

    // 环绕通知
    @Around("pointcut()")
    public Object checkLogin(ProceedingJoinPoint joinPoint) {
        try {
            //1: 从header里获取token
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
            assert attributes != null;
            HttpServletRequest request = attributes.getRequest();

            // 2：获取token
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                throw new RuntimeException("token 不允许空!!!");
            }

            // 3: jwt认证校验token是否合法
            Boolean tokenExpired = jwtOperatorService.validateToken(token);
            if (!tokenExpired) {
                throw new RuntimeException("token不合法");
            }

            // 4: jwt解析token中用户信息
            Claims claimsFromToken = jwtOperatorService.getClaimsFromToken(token);

            // 5: 解析出来的用户信息放入到request对象作用域
            // 目的：方便在springmvc通过request对象直接获取，可以免去再次去header中获取token再去解析
            request.setAttribute("userId", claimsFromToken.get("userId"));
            request.setAttribute("username", claimsFromToken.get("username"));
            request.setAttribute("role", claimsFromToken.get("role"));

            //6: 执行具体的springmvc的接口方法
            return joinPoint.proceed();
        } catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
