package com.trust.microservices.filter;


import com.trust.microservices.entity.User;
import com.trust.microservices.service.UserService;
import com.trust.microservices.serviceimpl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Authorization : Bearer lkflkasdfhkdhfko.akjkjasdfhdf.oaksdhfkjsad

        final String requestHeader = request.getHeader("Authorization");
        if(requestHeader==null || !requestHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = requestHeader.split("Bearer ")[1];
        //[Bearer ,'lkflkasdfhkdhfko.akjkjasdfhdf.oaksdhfkjsad']

        Long userId = jwtService.getUserIdFromToken(token);

        if(userId!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            User user = userService.getUserById(userId);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);

    }
}
