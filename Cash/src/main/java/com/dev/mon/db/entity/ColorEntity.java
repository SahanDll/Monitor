package com.dev.mon.db.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "loyalty_card_color_code")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColorEntity {
    @Column(name = "color_code")
    private String code;

//    @Column(name = "color_name")
//    private String name;

}
