package com.lombok.lombok.service;

import com.lombok.lombok.dao.CardDao;
import com.lombok.lombok.dto.CardDto;
import com.lombok.lombok.entity.Card;
import com.lombok.lombok.exceptions.CardNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class CardServiceImpl implements CardService{
    private CardDao cardDao;
    public CardServiceImpl(CardDao cardDao){
        this.cardDao = cardDao;
    }
    @Override
    public CardDto addCard(CardDto card) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                card.setCardNo(generateRandomNumber());
                return mapToCardDto(cardDao.saveAndFlush(mapToCardEntity(card)));   // flush forces the DB to check now
            } catch (DataIntegrityViolationException e) {
                attempts++;   // collision on card number → try a new number
            }
        }
        throw new IllegalStateException("Could not generate a unique card number");
    }

    @Override
    public void deleteByCardNo(long cardNo) {
        int deletedRows = cardDao.deleteByCardNo(cardNo);
        if(deletedRows == 0){
            throw new CardNotFoundException("Card not found.");
        }
    }

    @Override
    public CardDto updateCard(CardDto card) {
        return mapToCardDto(cardDao.save(mapToCardEntity(card)));
    }

    private Long generateRandomNumber() {
        return ThreadLocalRandom.current().nextLong(10_000_000_000L, 100_000_000_000L);
    }

    private CardDto mapToCardDto(Card card){
        CardDto cardDto = new CardDto.Builder().cardSrno(card.getCardSrno())
                .cardNo(card.getCardNo()).category(card.getCategory()).isMainCard(card.getIsMainCard())
                .primaryCardNo(card.getPrimaryCardNo()).chId(card.getCh_id()).isActive(card.getIsActive())
                .createdOn(card.getCreatedOn()).lastUpdatedOn(card.getLastUpdatedOn())
                .build();
        return  cardDto;
    }
    private Card mapToCardEntity(CardDto cardDto){
        Card card = Card.builder().category(cardDto.getCategory()).cardNo(cardDto.getCardNo())
                .cardSrno(cardDto.getCardSrno()).primaryCardNo(cardDto.getPrimaryCardNo()).isActive(cardDto.getIsActive())
                .isMainCard(cardDto.getIsMainCard()).createdOn(cardDto.getCreatedOn()).lastUpdatedOn(cardDto.getLastUpdatedOn())
                .ch_id(cardDto.getChId())
                .build();
        return card;
    }
}
