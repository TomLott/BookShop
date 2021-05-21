package com.tomlott.bookshop.user.appuser;

import com.tomlott.bookshop.user.registration.token.ConfirmationToken;
import com.tomlott.bookshop.user.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND, email)));
    }

    public String singUpUser(AppUser appUser){
        boolean exist = userRepository.findByEmail(appUser.getEmail()).isPresent();

        if (exist)
            throw new IllegalStateException("Email already taken")
                    ;
        String password = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(password);

        userRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO : Figure out how to send token

        return token;
    }

    public void enableAppUser(String email){
        userRepository.enableAppUser(email);
    }


}
