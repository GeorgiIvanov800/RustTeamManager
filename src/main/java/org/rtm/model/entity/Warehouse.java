package org.rtm.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rtm.model.enums.WarehouseName;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "warehouses")
public class Warehouse extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WarehouseName name;

    @OneToMany(mappedBy = "warehouse")
    private List<Sleeve> sleeves;
}
