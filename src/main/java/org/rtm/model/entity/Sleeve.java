package org.rtm.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rtm.model.enums.SleeveCondition;
import org.rtm.model.enums.SleeveType;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sleeves")
public class Sleeve extends BaseEntity {

    private String sequenceNumber; // Reference number (Satznummer)
    private String design;
    private String color; //Maybe make this ENUM when I have all the colors
    private String manufacturer;
    private String notes;
    private LocalDate manufactureDate;
    private Long width;

    @LastModifiedDate
    private LocalDate lastUsedAt;

    private Long kmStand;

    // Warehouse

    private String status;

    @Enumerated(EnumType.STRING)
    private SleeveType type;

    private SleeveCondition condition;
}
