package mx.edu.utez.alamacen.service.auth;

import mx.edu.utez.alamacen.config.ApiResponse;
import mx.edu.utez.alamacen.model.user.UserBean;
import mx.edu.utez.alamacen.security.jwt.JwtProvider;
import mx.edu.utez.alamacen.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService {
    private final UserService userService;
    private final JwtProvider provider;
    private final AuthenticationManager manager;

    public AuthService(UserService userService, JwtProvider provider, AuthenticationManager manager) {
        this.userService = userService;
        this.provider = provider;
        this.manager = manager;
    }
    @Transactional
    public ResponseEntity<ApiResponse> signIn(String username, String password){
        try {
            Optional<UserBean> foundUser = userService.findUserByUsername(username);
            if (foundUser.isEmpty())
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,
                        "UserOrPasswordNotFound"),HttpStatus.BAD_REQUEST);
            UserBean user = foundUser.get();
            if (!user.getStatus())
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,
                        "UserNotFound"),HttpStatus.BAD_REQUEST);
            if (!user.getBlocked())
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,
                        "UserBlocked"),HttpStatus.BAD_REQUEST);

            Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            SecurityContextHolder.getContext()
                    .setAuthentication(auth);
            String token = provider.generateToken(auth);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,false,"Token generado")
            ,HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            String message = "Credentials Mismatch";
            if (e instanceof DisabledException)
                message = "User Disabled";
            if (e instanceof AccountExpiredException)
                message = "Expiro";
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"User Blocked"),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
