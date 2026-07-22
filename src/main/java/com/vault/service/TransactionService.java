package com.vault.service;

import com.vault.dto.TransactionDto;
import com.vault.entity.Card;

import java.util.List;

public abstract class TransactionService {

    abstract public List<TransactionDto> getAllCardsTransactions(List<Card> card);

    abstract public TransactionDto addTransaction(TransactionDto transaction, Card card);
}
