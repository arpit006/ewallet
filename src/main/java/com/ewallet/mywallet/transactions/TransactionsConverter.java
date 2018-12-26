package com.ewallet.mywallet.transactions;

import org.springframework.stereotype.Component;

@Component
public class TransactionsConverter {

    public Transactions convertVoToEntity(TransactionsVo transactionsVo) {
        Transactions transactions = new Transactions();
        transactions.setSender(transactionsVo.getSender());
        transactions.setReceiver(transactionsVo.getReceiver());
        transactions.setTime(transactionsVo.getTime());
        transactions.setUuid(transactionsVo.getUuid());
        transactions.setAmount(transactionsVo.getAmount());
        return transactions;
    }

    public TransactionsVo convertEntityToVO(Transactions transactions) {
        TransactionsVo transactionsVo = TransactionsVo.builder()
                .uuid(transactions.getUuid())
                .sender(transactions.getSender())
                .receiver(transactions.getReceiver())
                .amount(transactions.getAmount())
                .time(transactions.getTime())
                .build();
        return transactionsVo;
    }
}
