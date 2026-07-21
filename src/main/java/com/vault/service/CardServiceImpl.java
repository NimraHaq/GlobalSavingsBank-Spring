package com.vault.service;

import com.vault.dao.CardDao;
import com.vault.dto.CardDto;
import com.vault.dto.CustomerDto;
import com.vault.dto.UserDto;
import com.vault.entity.Card;
import com.vault.exceptions.CardNotFoundException;
import com.vault.utils.Constants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService{
    private CardDao cardDao;
    private CustomerService customerService;
    public CardServiceImpl(CardDao cardDao, CustomerService customerService) {
        this.cardDao = cardDao;
        this.customerService = customerService;
    }
    @Override
    public CardDto addCard(UserDto user) {
        CardDto card = generateCard(user);
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


    private CardDto generateCard(UserDto user){
        CardDto cardDto = new CardDto();
        cardDto.setIsMainCard(Constants.OPTION_YES);
        cardDto.setCategory(Constants.CARD_CATEGORY_DEBIT);
        cardDto.setCardStatus(Constants.CARD_ACTIVE_STATUS);
        cardDto.setCustomerDto(user.getCustomerDto());
        return cardDto;

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

    @Override
    public List<CardDto> getCardsByChId(int chId) {
        List<CardDto> cards = cardDao.findCardsByCustomer(CustomerServiceImpl.customerDtoToEntityMapping(customerService.getCustomerByChId(chId))).stream().map(this::mapToCardDto).collect(Collectors.toList());
        return cards;
    }

    private Long generateRandomNumber() {
        return ThreadLocalRandom.current().nextLong(10_000_000_000L, 100_000_000_000L);
    }

    private CardDto mapToCardDto(Card card){
        CardDto cardDto = CardDto.builder().cardSrno(card.getCardSrno())
                .cardNo(card.getCardNo()).category(card.getCategory()).isMainCard(card.getIsMainCard())
                .primaryCardNo(card.getPrimaryCardNo()).customerDto(CustomerServiceImpl.customerEntityToDtoMapping(card.getCustomer()))
                .isActive(card.getIsActive()).cardStatus(card.getCardStatus())
                .createdOn(card.getCreatedOn()).lastUpdatedOn(card.getLastUpdatedOn())
                .build();
        return  cardDto;
    }
    private Card mapToCardEntity(CardDto cardDto){
        Card card = Card.builder().category(cardDto.getCategory()).cardNo(cardDto.getCardNo())
                .cardSrno(cardDto.getCardSrno()).primaryCardNo(cardDto.getPrimaryCardNo()).isActive(cardDto.getIsActive())
                .isMainCard(cardDto.getIsMainCard()).createdOn(cardDto.getCreatedOn()).lastUpdatedOn(cardDto.getLastUpdatedOn())
                .cardStatus(cardDto.getCardStatus())
                .customer(CustomerServiceImpl.customerDtoToEntityMapping(cardDto.getCustomerDto()))
                .build();
        return card;
    }
}
