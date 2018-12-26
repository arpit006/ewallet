package com.ewallet.mywallet.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private ITransactionsService iTransactionsService;

    @GetMapping("/{userName}")
    Flux<List<TransactionsVo>> showTransactionsForUserName(@PathVariable String userName) {
        return iTransactionsService.showTransactionsByUSerName(userName);
    }
    @GetMapping
    Flux<List<TransactionsVo>> showAllTransactions() {
        return iTransactionsService.showAllTransactions();
    }
}
