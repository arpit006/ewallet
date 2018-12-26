package com.ewallet.mywallet.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, String> {

    List<Transactions> findAllBySenderOrReceiver(String sender, String receiver);
}
