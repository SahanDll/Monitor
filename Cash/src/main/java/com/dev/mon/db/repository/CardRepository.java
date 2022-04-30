package com.dev.mon.db.repository;

import com.dev.mon.db.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sun.reflect.generics.repository.AbstractRepository;

public interface CardRepository extends JpaRepository<Long, CardEntity> {

    @Modifying
    @Query("update LoyaltyCardEntity lc set lc.cardBackImagePath = :fi, lc.cardFrontImagePath = :bi where lc.id = :id")
    void updateImagePath(@Param("fi") String frontImagePath, @Param("bi") String backImagePath, @Param("id") Long id);

    @Modifying
    @Query("update LoyaltyCardEntity lc set lc.cardFrontImagePath = :fi where lc.id = :id")
    void updateFrontImagePath(@Param("fi") String frontImagePath, @Param("id") Long id);

    @Modifying
    @Query("update LoyaltyCardEntity lc set lc.cardBackImagePath = :bi where lc.id = :id")
    void updateBackImagePath(@Param("bi") String backImagePath, @Param("id") Long id);

    @Query("select count(lc) from LoyaltyCardEntity lc where lc.userId = :ui")
    Long findLoyaltyCardsCountForUser(@Param("ui") Long userId);

}
