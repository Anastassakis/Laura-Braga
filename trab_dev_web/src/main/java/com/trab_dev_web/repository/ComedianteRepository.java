package com.trab_dev_web.repository;

import com.trab_dev_web.model.Comediante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComedianteRepository extends JpaRepository<Comediante, Long> {
}
