package com.institute.school.Repository;
import com.institute.school.model.SchoolDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRepo extends JpaRepository<SchoolDTO, Long> {

    List <SchoolDTO> findByName(String name);
    Optional<SchoolDTO> findByPhone (Long phone);
    Optional<SchoolDTO> findByPercentage (Double percentage);
}
