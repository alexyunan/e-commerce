package org.alexgiou.ecommerce.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/24/2024
 */
@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    /**
     * Extracts the username (subject) from the JWT token.
     *
     * @param token The JWT token from which to extract the username.
     * @return The username extracted from the token.
     * @implNote This method extracts the subject claim from the token, which typically represents the user's identity.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from the JWT token using a claim resolver function.
     *
     * @param token         The JWT token from which to extract the claim.
     * @param claimResolver The function to resolve the desired claim from the token's claims.
     * @param <T>           The type of the claim to extract.
     * @return The extracted claim.
     * @implNote This method provides flexibility to extract any custom claim from the JWT token by providing a claim resolver function.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Parses and extracts all claims from the JWT token.
     *
     * @param token The JWT token to parse.
     * @return All claims extracted from the token.
     * @implNote This method parses the JWT token, validates its signature, and retrieves all claims embedded within it.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.
                parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Generates a JWT token for the given user details.
     *
     * @param userDetails The user details for whom to generate the token.
     * @return The generated JWT token.
     * @implNote This method generates a JWT token containing standard claims such as subject, expiration, and authorities based on the provided user details.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token with additional claims for the given user details.
     *
     * @param claims      Additional claims to include in the token.
     * @param userDetails The user details for whom to generate the token.
     * @return The generated JWT token with the specified claims.
     * @implNote This method allows customization of the JWT token by including additional claims along with the standard claims based on the provided user details.
     */
    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return buildToken(claims, userDetails, jwtExpiration);
    }

    /**
     * Builds a JWT token with the specified claims and expiration time.
     *
     * @param extraClaims   Additional claims to include in the token.
     * @param userDetails   The user details for whom to generate the token.
     * @param jwtExpiration The expiration time of the token, in milliseconds.
     * @return The built JWT token with the specified claims and expiration.
     * @implNote This method constructs a JWT token with the provided claims, including subject, expiration, authorities, and any additional custom claims, and signs it with the application's signing key.
     */
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long jwtExpiration) {
        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .claim("authorities", authorities)
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
