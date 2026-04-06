package bank.management.system.controller;

import bank.management.system.dto.SignupRequest;
import bank.management.system.entity.Account;
import bank.management.system.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for user registration
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("signupRequest", new SignupRequest());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupRequest signupRequest,
                        BindingResult result,
                        RedirectAttributes redirectAttributes,
                        Model model) {
        if (result.hasErrors()) {
            return "signup";
        }

        try {
            Account account = userService.registerUser(signupRequest);
            
            // Show account number and PIN to user (one-time display)
            redirectAttributes.addFlashAttribute("accountNumber", account.getAccountNumber());
            redirectAttributes.addFlashAttribute("pin", account.getPin());
            redirectAttributes.addFlashAttribute("success", true);
            
            return "redirect:/signup-success";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/signup-success")
    public String signupSuccess() {
        return "signup-success";
    }
}
