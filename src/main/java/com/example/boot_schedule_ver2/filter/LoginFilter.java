package com.example.boot_schedule_ver2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

/**
 *  로그인 인증 필터
 *  - 인증이 필요한 요청에 대해 사용자의 로그인 여부를 확인하고, 미인증 사용자의 접근을 차단합니다.
 *  @Slf4j: Lombok 어노테이션으로, 로깅 기능을 쉽게 사용할 수 있도록 해줍니다.
 */
@Slf4j
public class LoginFilter implements Filter {

    // 인증하지 않아도 될 URL (White List)
    private static final String[] WHITE_LIST = {"/", "/users", "/users/login", "/users/logout"};
    // 세션 키 (사용자 ID 저장 시 사용)
    private static final String SESSION_KEY = "userId";

    /**
     *  필터 실행 메서드
     *  - 요청이 필터를 거칠 때마다 실행됩니다.
     *  @param request 서블릿 요청 객체
     *  @param response 서블릿 응답 객체
     *  @param chain 필터 체인 (다음 필터로 요청을 전달하는 역할)
     *  @throws IOException
     *  @throws ServletException
     */
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        // 다운 캐스팅 (HttpServletRequest, HttpServletResponse 사용을 위해)
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI(); // 요청 URI

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행: {}", requestURI);

        // White List 에 포함된 URL 인지 확인
        if (!isWhiteList(requestURI)) {
            log.info("인증 체크 URL: {}", requestURI);

            // 세션 확인
            HttpSession session = httpRequest.getSession(false); // 이미 존재하는 세션 가져오기 (없으면 null 반환)

            // 미인증 사용자 접근 시
            if (session == null || session.getAttribute(SESSION_KEY) == null) {
                log.warn("미인증 사용자 접근: {}", requestURI);
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized 상태 코드 설정
                return; // 다음 필터로 진행하지 않고 요청 종료
            }

            log.info("인증된 사용자 접근: {}", requestURI);
        }

        // 다음 필터로 요청 전달
        chain.doFilter(request, response);
    }

    /**
     *  White List 에 포함된 URL 인지 확인하는 메서드
     *  - PatternMatchUtils.simpleMatch 를 사용하여 패턴 매칭 수행
     *  @param requestURI 요청 URI
     *  @return White List 에 포함되면 true, 아니면 false
     */
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
