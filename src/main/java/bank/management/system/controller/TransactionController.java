package bank.management.system.controller;

import bank.management.system.dto.PinChangeRequest;
import bank.management.system.dto.TransactionRequest;
import bank.management.system.entity.Account;
import bank.management.system.entity.Transaction;
import bank.management.system.service.AccountService;
import bank.management.system.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Controller for transaction operations
 */
@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        String accountNumber = (String) session.getAttribute("accountNumber");
        if (accountNumber == null) {
            return "redirect:/login";
        }

        Optional<Account> account = accountService.getAccount(accountNumber);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
            model.addAttribute("balance", transactionService.getBalance(accountNumber));
        }

        return "dashboard";
    }

    @GetMapping("/deposit")
    public String showDepositPage(Model model) {
        model.addAttribute("transactionRequest", new TransactionRequest());
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@Valid @ModelAttribute TransactionRequest transactionRequest,
                         BindingResult result,
                         HttpSession session,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if (result.hasErrors()) {
            return "deposit";
        }

        String accountNumber = (String) session.getAttribute("accountNumber");
        if (accountNumber == null) {
            return "redirect:/login";
        }

        try {
            Transaction transaction = transactionService.deposit(
                accountNumber,
                "", // PIN not needed for deposit in session
                transactionRequest.getAmount()
            );

            redirectAttributes.addFlashAttribute("success", 
                "Rs. " + transactionRequest.getAmount() + " deposited successfully");
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Deposit failed: " + e.getMessage());
            return "deposit";
        }
    }

    @GetMapping("/withdraw")
    public String showWithdrawPage(Model model) {
        model.addAttribute("transactionRequest", new TransactionRequest());
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@Valid @ModelAttribute TransactionRequest transactionRequest,
                          BindingResult result,
                          HttpSession session,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if (result.hasErrors()) {
            return "withdraw";
        }

        String accountNumber = (String) session.getAttribute("accountNumber");
        if (accountNumber == null) {
            return "redirect:/login";
        }

        try {
            Transaction transaction = transactionService.withdraw(
                accountNumber,
                "",
                transactionRequest.getAmount()
            );

            redirectAttributes.addFlashAttribute("success",
                "Rs. " + transactionRequest.getAmount() + " withdrawn successfully");
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "withdraw";
        }
    }

    @GetMapping("/balance")
    public String showBalance(HttpSession session, Model model) {
        String accountNumber = (String) session.getAttribute("accountNumber");
        if (accountNumber == null) {
            return "redirect:/login";
        }

        Double balance = transactionService.getBalance(accountNumber);
        model.addAttribute("balance", balance);
        model.addAttribute("accountNumber", accountNumber);

        return "balance";
    }

    @GetMapping("/fastcash")
    public String showFastCashPage() {
        return "fastcash";
    }

    @PostMapping("/fastcash")
    public String fastCash(@RequestParam("amount") Double amount,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        String accountNumber = (String) session.getAttribute("accountNumber");
        if (accountNumber == null) {
            return "redirect:/login";
        }

        try {
            transactionService.withdraw(accountNumber, "", amount);
            redirectAttributes.addFlashAttribute("success",
                "Rs. " + amount + " withdrawn successfully");
            return "redirect:/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/fastcash";
        }
    }

    @GetMapping("/pin")
    public String showPinChangePage(Model model) {
        model.addAttribute("pinChangeRequest", new PinChangeRequest());
        return "pin";
    }

    @PostMapping("/pin")
    public String changePin(@Valid @ModelAttribute PinChangeRequest pinChangeRequest,
                           BindingResult result,
                           HttpSession session,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        if (result.hasErrors()) {
            return "pin";
        }

        String accountNumber = (String) session.getAttribute("accountNumber");
        if (accountNumber == null) {
            return "redirect:/login";
        }

        // Validate new PIN matches confirm PIN
        if (!pinChangeRequest.getNewPin().equals(pinChangeRequest.getConfirmPin())) {
            model.addAttribute("error", "New PIN and Confirm PIN do not match");
            return "pin";
        }

        try {
            boolean success = accountService.changePin(
                accountNumber,
                pinChangeRequest.getCurrentPin(),
                pinChangeRequest.getNewPin()
            );

            if (success) {
                redirectAttributes.addFlashAttribute("success", "PIN changed successfully");
                return "redirect:/dashboard";
            } else {
                model.addAttribute("error", "Current PIN is incorrect");
                return "pin";
            }
        } catch (Exception e) {
            model.addAttribute("error", "PIN change failed: " + e.getMessage());
            return "pin";
        }
    }

    @GetMapping("/mini")
    public String showMiniStatement(HttpSession session, Model model) {
        String accountNumber = (String) session.getAttribute("accountNumber");
        if (accountNumber == null) {
            return "redirect:/login";
        }

        List<Transaction> transactions = transactionService.getMiniStatement(accountNumber);
        Double balance = transactionService.getBalance(accountNumber);

        model.addAttribute("transactions", transactions);
        model.addAttribute("balance", balance);
        model.addAttribute("accountNumber", accountNumber);

        return "mini";
    }
}
