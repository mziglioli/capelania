package com.capelania.config.filter;

import com.capelania.config.security.TokenAuthenticationService;
import com.capelania.form.LoginForm;
import com.capelania.response.DefaultResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService tokenAuthenticationService;
	private final ObjectMapper objectMapper;

	public LoginFilter(TokenAuthenticationService tokenAuthenticationService, AuthenticationManager authManager, ObjectMapper objectMapper) {
		super(new AntPathRequestMatcher("/public/login"));
		this.objectMapper = objectMapper;
		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		try {
			final LoginForm form = objectMapper.readValue(request.getInputStream(), LoginForm.class);
			final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword());
			return getAuthenticationManager().authenticate(loginToken);
		} catch (IOException e) {
			throw new UsernameNotFoundException("User not parsed correctly", e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
		tokenAuthenticationService.addAuthentication(response, authResult.getName());
//		CsrfHeaderFilter.addCsrfCookie(response);
		response.setStatus(HttpStatus.OK.value());
		try {
			response.getOutputStream().println(objectMapper.writeValueAsString(DefaultResponse.builder().data(authResult.getPrincipal()).success(true).build()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}