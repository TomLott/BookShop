package com.tomlott.bookshop.user.registration;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("request", new RegistrationRequest("", "", ""));
        return "registration/index";
    }

    @PostMapping( )
    public String register (@ModelAttribute("request") @Valid RegistrationRequest request, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "registration/index";
        }
        String token = registrationService.register(request);
        model.addAttribute("token", token);
        return "registration/token";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return "registration/confirm";
    }
}
