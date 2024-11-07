package api.nutech.service;

import api.nutech.model.Balance;
import api.nutech.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    public Optional<Balance> getBalance(UUID userId) {
        return balanceRepository.findByUserId(userId);
    }

    public Balance topUp(UUID userId, BigDecimal amount) {
        Optional<Balance> balanceOpt = balanceRepository.findByUserId(userId);
        Balance balance = balanceOpt.orElse(new Balance());
//        balance.setBalance(balance.getBalance().add(amount));
        return balanceRepository.save(balance);
    }
}
