package com.energyControl.energycontrol.repository;

import com.energyControl.energycontrol.domains.Consume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumeRepository extends JpaRepository<Consume, Integer>{

    public List<Consume> findByUserId(Integer id);

}

