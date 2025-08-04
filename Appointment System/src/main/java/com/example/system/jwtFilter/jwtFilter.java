package com.example.system.jwtFilter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.system.jwtUtil.jwtUtil;

import java.io.IOException;

@Component
public class jwtFilter extends OncePerRequestFilter {

	 @Autowired
	    private jwtUtil jwtutil;

	    private final UserDetailsService userDetailsService;

	    public jwtFilter(@Lazy UserDetailsService userDetailsService) {
	        this.userDetailsService = userDetailsService;
	    }
	    @Override
	    protected boolean shouldNotFilter(HttpServletRequest request) {
	        String path = request.getServletPath();
	        return path.equals("/auth/login") || path.equals("/auth/register");
	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    FilterChain chain) throws ServletException, IOException {
	    	System.out.println("📍 Filter called on path: " + request.getServletPath());
	        // Skip filtering for login or register endpoints
	        String path = request.getServletPath();
	        if (path.equals("/auth/login") || path.equals("/auth/register")) {
	            chain.doFilter(request, response);
	            return;
	        }

	        String authHeader = request.getHeader("Authorization");

	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            String token = authHeader.substring(7);
	            String username = jwtutil.extractUsername(token);

	            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	                if (jwtutil.validateToken(token)) {
	                    UsernamePasswordAuthenticationToken authToken =
	                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    SecurityContextHolder.getContext().setAuthentication(authToken);
	                }
	            }
	        }

	        chain.doFilter(request, response);
	    }
}