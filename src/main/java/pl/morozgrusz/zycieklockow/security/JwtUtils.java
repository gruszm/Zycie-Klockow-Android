package pl.morozgrusz.zycieklockow.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.List;

public class JwtUtils
{
    private static final String SECRET = "tajnyKlucz"; // Użyj bezpiecznego klucza w produkcji
    private static final long EXPIRATION_TIME = 900_000; // 15 minut

    public static String createToken(String userId, List<String> roles)
    {
        return JWT.create()
                .withSubject("UserDetails")
                .withClaim("userId", userId)
                .withArrayClaim("userRoles", roles.toArray(new String[0]))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET));
    }


    public static UserDetails readToken(String token)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC512(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject("UserDetails")
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            UserDetails ud = new UserDetails(jwt.getClaim("userId").asString(), jwt.getClaim("userRoles").asList(String.class));

            return ud;
        }
        catch (JWTVerificationException exception)
        {
            // Loguj błąd lub rzuć wyjątek
            return null;
        }
    }
}
