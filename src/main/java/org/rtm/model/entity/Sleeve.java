package org.rtm.model.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String sequenceNumber; // Reference number (Satznummer)

    @Column(nullable = false)
    private String design;

    @Column(nullable = false)
    private String color; //Maybe make this ENUM when I have all the colors

    private String manufacturer;

    @Column(nullable = false)
    private String notes;

    @Column(nullable = false)
    private LocalDate manufactureDate;

    @Column(nullable = false)
    private Long width;

    @LastModifiedDate
    private LocalDate lastUsedAt;

    private Long kmStand;

    @ManyToOne()
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    private String status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SleeveType type;

    @Column(nullable = false)
    private SleeveCondition condition;
}
