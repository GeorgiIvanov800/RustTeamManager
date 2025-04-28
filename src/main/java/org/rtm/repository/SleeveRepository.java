package org.rtm.repository;

import org.rtm.model.entity.Sleeve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SleeveRepository extends JpaRepository<Sleeve, Long> {

    List<Sleeve> findAllBySequenceNumber(int sequenceNumber);


    boolean existsBySleeveNumber(Integer sleeveNumber);
}
