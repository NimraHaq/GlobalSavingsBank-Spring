package com.lombok.lombok.dao;

import com.lombok.lombok.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cards")
public interface CardDao extends JpaRepository<Card, Long> {
    Card findByCardNo(long cardNo);
    int deleteByCardNo(long cardNo);
}
