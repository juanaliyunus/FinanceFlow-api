package com.enigma.loan.security;

import com.enigma.loan.dto.response.JWTClaims;
import com.enigma.loan.entity.User;
import com.enigma.loan.service.JWTService;
import com.enigma.loan.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserService userService;
    final String AUTH_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
            try {
                String bearerToken = request.getHeader(AUTH_HEADER);
                if (bearerToken != null && jwtService.verifyJwtToken(bearerToken)){
                    JWTClaims jwtClaims = jwtService.getClaimsByToken(bearerToken);
                    User user = userService.loadUserById(jwtClaims.getUserId());
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            null,
                            user.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }catch (Exception e){
                log.error("Cannot set user authentication: {}", e.getMessage());
            }
            filterChain.doFilter(request, response);
    }
}
