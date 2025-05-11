package org.rtm.repository;

import org.rtm.model.entity.Sleeve;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SleeveRepository extends JpaRepository<Sleeve, Long> {

    List<Sleeve> findAllBySequenceNumber(int sequenceNumber);


    boolean existsBySleeveNumber(Integer sleeveNumber);

    Page<Sleeve> findAllByWarehouseId(Long warehouseId, Pageable pageable);
}
