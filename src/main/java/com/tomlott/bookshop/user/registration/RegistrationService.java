package com.tomlott.bookshop.user.registration;

import com.tomlott.bookshop.user.appuser.AppUser;
import com.tomlott.bookshop.user.appuser.AppUserRole;
import com.tomlott.bookshop.user.appuser.AppUserService;
import com.tomlott.bookshop.user.registration.token.ConfirmationToken;
import com.tomlott.bookshop.user.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail)
            throw new IllegalStateException(String.format("email is not valid - %s", request.getEmail())) ;

        String token = appUserService.singUpUser(new AppUser(
                request.getFirstName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));
//        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;

        return token;
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(()-> new IllegalStateException("Token not found"));

        if (confirmationToken.getConfirmedAt() != null)
            throw new IllegalStateException("Token has been confirmed already");

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token is expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }

}
