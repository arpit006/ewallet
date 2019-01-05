package com.ewallet.mywallet.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionsService implements ITransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private TransactionsConverter transactionsConverter;

    @Override
    public TransactionsVo buildTransaction(TransactionsVo transactionsVo) {
        return transactionsConverter.convertEntityToVO(transactionsRepository.save(transactionsConverter.convertVoToEntity(transactionsVo)));
    }

    @Override
    public Flux<List<TransactionsVo>> showTransactionsByUSerName(String userName) {
        List<Transactions> transactions = transactionsRepository.findAllBySenderOrReceiver(userName,userName);
        List<TransactionsVo> transactionsVos = new ArrayList<>();
        for(Transactions t : transactions) {
            transactionsVos.add(transactionsConverter.convertEntityToVO(t));
        }
        return Flux.just(transactionsVos);
    }

    @Override
    public Flux<TransactionsVo> showAllTransactions() {
        List<Transactions> transactions = transactionsRepository.findAll();
        List<TransactionsVo> transactionsVos = new ArrayList<>();
        for (Transactions t : transactions) {
            transactionsVos.add(transactionsConverter.convertEntityToVO(t));
        }
       return Flux.fromIterable(transactionsVos);


    }
}
