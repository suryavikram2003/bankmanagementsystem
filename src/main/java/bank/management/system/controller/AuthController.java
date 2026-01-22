package bank.management.system.controller;

import bank.management.system.dto.LoginRequest;
import bank.management.system.entity.Account;
import bank.management.system.service.AccountService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * Controller for authentication operations
 */
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginRequest loginRequest,
                       BindingResult result,
                       HttpSession session,
                       Model model) {
        if (result.hasErrors()) {
            return "login";
        }

        Optional<Account> account = accountService.validateAccount(
            loginRequest.getAccountNumber(),
            loginRequest.getPin()
        );

        if (account.isPresent()) {
            // Store account info in session
            session.setAttribute("accountNumber", account.get().getAccountNumber());
            session.setAttribute("formNo", account.get().getFormNo());
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid Account Number or PIN");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
