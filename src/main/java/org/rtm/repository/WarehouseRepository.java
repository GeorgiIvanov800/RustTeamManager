package org.rtm.repository;

import org.rtm.model.entity.Warehouse;
import org.rtm.model.enums.WarehouseName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Warehouse getWarehouseByName(WarehouseName name);


}
