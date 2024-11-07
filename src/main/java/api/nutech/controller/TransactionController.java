package api.nutech.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@Tag(name = "Module Transaction")
public class TransactionController {

    @GetMapping("/balance")
    public String getBalance() {
        return "User balance";
    }

    @PostMapping("/topup")
    public String topUp() {
        return "Top-up successful";
    }

    @PostMapping("/transaction")
    public String createTransaction() {
        return "Transaction created";
    }

    @GetMapping("/transaction/history")
    public String getTransactionHistory() {
        return "Transaction history";
    }
}
