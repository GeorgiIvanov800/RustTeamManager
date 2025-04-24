package org.rtm.repository;

import org.rtm.model.entity.Sleeve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleeveRepository extends JpaRepository<Sleeve, Long> {
}
