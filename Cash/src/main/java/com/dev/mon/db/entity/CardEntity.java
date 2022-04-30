package com.dev.mon.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "loyalty_cards")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "back_image")
    private String cardBackImagePath;

    @Column(name = "front_image")
    private String cardFrontImagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id" , nullable =  false)
    @NotNull
    private ColorEntity color;


//    @PrePersist
//    public void prePersist() {
//        setCreatedDate(new Date());
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        setUpdatedDate(new Date());
//    }

}
