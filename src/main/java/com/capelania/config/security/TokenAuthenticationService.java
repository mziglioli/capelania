package com.capelania.config.security;

import com.capelania.model.User;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

@Service
public class TokenAuthenticationService {

	// COOKIE
	public static final String COOKIE_XSRF_TOKEN = "XSRF-TOKEN";
	public static final String COOKIE_X_XSRF_TOKEN = "X-XSRF-TOKEN";
	//seconds, minutes, hs, days
	public static final int COOKIE_TIME = (60 * 60 * 24 * 1);
	public static final String COOKIE_PATH = "/";
	public static final String COOKIE_AUTH_NAME = "X-AUTH-TOKEN";
	public static final String COOKIE_USER_ID = "USER-ID";

	@Autowired
	private TokenHandler tokenHandler;

	public void addAuthentication(HttpServletResponse response, String username) {
		String jwt = tokenHandler.createTokenForUser(username);

		Cookie cookie = new Cookie(COOKIE_AUTH_NAME, jwt);
		cookie.setPath(COOKIE_PATH);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(COOKIE_TIME);
		response.addCookie(cookie);
		response.addHeader(COOKIE_AUTH_NAME, jwt);
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, COOKIE_AUTH_NAME);
		if (cookie != null) {
			String token = cookie.getValue();
			if (token != null) {
				final User user = tokenHandler.parseUserFromToken(token);
				if (user != null) {
					return new UserAuthentication(user);
				}
			}
		}
		return null;
	}
}