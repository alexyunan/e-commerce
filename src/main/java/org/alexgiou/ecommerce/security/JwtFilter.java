package org.alexgiou.ecommerce.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/24/2024
 */
@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    /**
     * Performs filtering logic for incoming HTTP requests.
     * This method checks if the request path contains "/api/auth".
     * If it does, it allows the request to continue down the filter chain without any further processing.
     * Otherwise, it extracts the JWT token from the request's Authorization header, verifies its validity,
     * and if valid, authenticates the user by setting the authentication token in the security context.
     *
     * @param request     The HTTP servlet request.
     * @param response    The HTTP servlet response.
     * @param filterChain The filter chain for passing the request and response to the next filter.
     * @throws ServletException If an error occurs during the servlet processing.
     * @throws IOException      If an I/O error occurs during the filtering process.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {


        // Check if the request path contains "/api/auth"
        if (request.getServletPath().contains("/api/auth")) {
            // If it does, allow the request to continue down the filter chain
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token from the Authorization header
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String email;

        // If the Authorization header is missing or does not start with "Bearer ", allow the request to continue
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token from the Authorization header
        jwt = authHeader.substring(7);
        // Extract the user email from the JWT token
        email = jwtService.extractUsername(jwt);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details from the user service using the extracted email
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            // If the JWT token is valid for the user, create an authentication token
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication token in the security context
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
        }
    }
}
