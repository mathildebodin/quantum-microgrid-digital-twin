package com.quantummicrogrid.repository;

import com.quantummicrogrid.model.Grid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GridRepository extends JpaRepository<Grid,Long>{
}
