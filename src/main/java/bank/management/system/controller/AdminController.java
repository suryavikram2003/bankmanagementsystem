package bank.management.system.controller;

import bank.management.system.entity.Account;
import bank.management.system.service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller for admin operations
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AccountService accountService;

    @GetMapping("/login")
    public String showAdminLoginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String adminLogin(@RequestParam String adminId,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        // Admin authentication - credentials should be externalized to environment variables
        // For demo purposes, using simple comparison (in production, use database with hashed passwords)
        String expectedAdminId = System.getenv().getOrDefault("ADMIN_ID", "admin");
        String expectedPassword = System.getenv().getOrDefault("ADMIN_PASSWORD", "admin123");
        
        if (expectedAdminId.equals(adminId) && expectedPassword.equals(password)) {
            session.setAttribute("adminLoggedIn", true);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid Admin ID or Password");
            return "admin/login";
        }
    }

    @GetMapping("/dashboard")
    public String showAdminDashboard(HttpSession session, Model model) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        List<Account> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);

        return "admin/dashboard";
    }

    @PostMapping("/delete-account")
    public String deleteAccount(@RequestParam String accountNumber,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        try {
            boolean success = accountService.deleteAccount(accountNumber);
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Account deleted successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "Account not found");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Delete failed: " + e.getMessage());
        }

        return "redirect:/admin/dashboard";
    }

    @GetMapping("/logout")
    public String adminLogout(HttpSession session) {
        session.removeAttribute("adminLoggedIn");
        return "redirect:/admin/login?logout";
    }
}
