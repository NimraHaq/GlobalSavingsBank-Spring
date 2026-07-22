package com.vault.dao;

import com.vault.entity.Card;
import com.vault.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "transactions")
public interface TransactionDao extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByCardIn(List<Card> cards);
}
