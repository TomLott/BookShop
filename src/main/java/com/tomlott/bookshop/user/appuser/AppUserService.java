package com.tomlott.bookshop.user.appuser;

import com.tomlott.bookshop.user.registration.token.ConfirmationToken;
import com.tomlott.bookshop.user.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

    @PostConstruct
    private void init(){
        if (userRepository.findByEmail("admin@mail.ru").isPresent())
            return ;
        AppUser user = new AppUser();
        user.setFirstName("admin");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        user.setEmail("admin@mail.ru");
        user.setAppUserRole(AppUserRole.ADMIN);
        try {
            Field enabled = user.getClass().getDeclaredField("enabled");
            enabled.setAccessible(true);
            enabled.set(user, (Boolean) true);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Cannot create admin");
        }

        userRepository.save(user);

    }


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

    public List<AppUser> getUsers(){
        return userRepository.findAll();
    }


}
