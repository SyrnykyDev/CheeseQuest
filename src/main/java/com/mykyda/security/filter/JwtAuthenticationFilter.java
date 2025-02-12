package com.mykyda.security.filter;

import com.mykyda.security.database.entity.User;
import com.mykyda.security.service.JwtService;
import com.mykyda.security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;


//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        Cookie cookie = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[]{}))
//                .filter(cookieElement -> "accessToken".equals(cookieElement.getName()))
//                .findFirst().orElse(null);
//        if (cookie != null) {
//            String jwtToken = Optional.ofNullable(cookie.getValue()).orElse("");
//            if (!jwtToken.isBlank() && jwtService.validateToken(jwtToken)) {
//                User user;
//                try {
//                    user = userService.findByEmail(jwtService.extractUsername(jwtToken));
//                } catch (Exception e) {
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    response.setHeader("Set-Cookie", "accessToken=unauthorized;expires=Thu, 01 Jan 1970 00:00:01 GMT;");
//                    filterChain.doFilter(request, response);
//                    return;
//                }
//                if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken((Principal) user::getUsername,
//                            null,
//                            user.getAuthorities()
//                    );
//                    SecurityContextHolder.getContext().setAuthentication(token);
//                }
//                filterChain.doFilter(request, response);
//            } else {
//                filterChain.doFilter(request, response);
//            }
//        } else {
//            filterChain.doFilter(request, response);
//        }
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}
