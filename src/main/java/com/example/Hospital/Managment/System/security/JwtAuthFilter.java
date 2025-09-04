package com.example.Hospital.Managment.System.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.Hospital.Managment.System.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;

        private final HandlerExceptionResolver handlerExceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                try{
        log.info("incoming request : {}", request.getRequestURI());

        final String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        final String token = authHeader.substring(7);
        String username=authUtil.getUsernameFromToken(token);

        if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){
            var user=userRepository.findByUsername(username).orElseThrow();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
            );
            // if(user !=null && AuthUtil.isTokenValid(token, userRepository)){
                // var authToken=AuthUtil.getAuthenticationToken(token, user);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            // }
        }
        filterChain.doFilter(request, response);
        // if(!AuthUtil.isTokenValid(token, userRepository)){
    }
    catch(Exception ex){
        handlerExceptionResolver.resolveException(request, response, null, ex);
    }
    }
    
}
