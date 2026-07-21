package com.vault.service;

import com.vault.dto.CardDto;
import com.vault.dto.UserDto;
import com.vault.entity.Card;

import java.util.List;

public interface CardService {
    CardDto addCard(UserDto user);
    void deleteByCardNo(long cardNo);

    CardDto updateCard(CardDto card);

    List<CardDto> getCardsByChId(int chId);
}
