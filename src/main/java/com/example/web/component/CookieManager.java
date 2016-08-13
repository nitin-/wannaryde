package com.example.web.component;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CookieManager {

    @Autowired
    Environment env;
    
    private static final String SESSION_COOKIE_NAME = "session.cookie.name";
    private static final String SESSION_COOKIE_AGE = "session.cookie.age";
    private static final String SESSION_COOKIE_PATH = "session.cookie.path";
    private static final String SESSION_COOKIE_DOMAIN = "session.cookie.domain";
    
    public String checkCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String cookieName = env.getProperty(SESSION_COOKIE_NAME);
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    
    public String setCookie(HttpServletRequest request, HttpServletResponse response) {
		String cookieName = env.getProperty(SESSION_COOKIE_NAME);
        String uniqueSessionId = getUUID();
        Cookie ck = new Cookie(cookieName, uniqueSessionId);
        ck.setMaxAge(Integer.valueOf(env.getProperty(SESSION_COOKIE_AGE)));
        ck.setPath(env.getProperty(SESSION_COOKIE_PATH));
        ck.setDomain(env.getProperty(SESSION_COOKIE_DOMAIN));
        response.addCookie(ck);
        return uniqueSessionId;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
        	String cookieName = env.getProperty(SESSION_COOKIE_NAME);
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())){
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }
}
