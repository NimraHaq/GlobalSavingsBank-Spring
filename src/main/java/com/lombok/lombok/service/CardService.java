package com.lombok.lombok.service;

import com.lombok.lombok.dto.CardDto;
import com.lombok.lombok.entity.Card;

public interface CardService {
    CardDto addCard(CardDto card);
    void deleteByCardNo(long cardNo);

    CardDto updateCard(CardDto card);
}
