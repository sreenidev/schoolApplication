package com.institute.school.controller;

import com.institute.school.Repository.SchoolRepo;
import com.institute.school.exception.SchoolException;
import com.institute.school.model.AdminDTO;
import com.institute.school.model.SchoolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired private SchoolRepo schoolRepo;

    //fetchAll details
    @GetMapping("/admin/fetchAll")
    public List<SchoolDTO> fetchAllDetails() {
        return schoolRepo.findAll();
    }

    //update status by admin
    @PutMapping("/statusDetails/admin/{phone}")
    public ResponseEntity<SchoolDTO> updateStatusDetails(@PathVariable Long phone, @RequestBody AdminDTO statusDetails){
        SchoolDTO schoolDTO = schoolRepo.findByPhone(phone).orElseThrow(()-> new SchoolException(phone + "not found"));
        schoolDTO.getStatusDetails().setStatus(statusDetails.getStatus());
        schoolDTO.getStatusDetails().setStatusMessage(statusDetails.getStatusMessage());
        schoolRepo.save(schoolDTO);
        return ResponseEntity.ok(schoolDTO);
    }
}
