package com.example.boot_schedule_ver2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    //인증하지 않아도 될 URL
    private static final String[] WHITE_LIST = {"/", "/users", "/users/login", "/users/logout"};
    //세션키
    private static final String SESSION_KEY = "userId";

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행: {}", requestURI);

        //로그인을 해야하는 url인지 확인
        //만약 WHITE_LIST에 포함되여있을 경우 실행 안함
        if (!isWhiteList(requestURI)) {
            log.info("인증 체크 URL: {}", requestURI);

            //로그인 확인
            //로그인되어 있으면 세션이 존재하는 것
            //존재하는 세션 get
            HttpSession session = httpRequest.getSession(false);

            //로그인 하지 않은 사용자는 세션이 null
            if (session == null || session.getAttribute(SESSION_KEY) == null) {
                log.warn("미인증 사용자 접근: {}", requestURI);
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                return;
            }

            log.info("인증된 사용자 접근: {}", requestURI);
        }

        chain.doFilter(request, response);
    }

    //WHITE_LIST에 있는 url인지 확인하기 위한 메서드
    //포함되면 true
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
