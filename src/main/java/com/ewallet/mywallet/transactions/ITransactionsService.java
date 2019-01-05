package com.ewallet.mywallet.transactions;

import reactor.core.publisher.Flux;

import java.util.List;

public interface ITransactionsService {
    TransactionsVo buildTransaction(TransactionsVo transactionsVo);

    Flux<List<TransactionsVo>> showTransactionsByUSerName(String userName);

    Flux<TransactionsVo> showAllTransactions();
}
