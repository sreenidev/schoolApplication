package com.institute.school.controller;
import com.institute.school.Repository.SchoolRepo;
import com.institute.school.exception.SchoolException;
import com.institute.school.model.SchoolDTO;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class SchoolController {

    @Autowired
    private SchoolRepo schoolRepo;
    @Autowired
    private ModelMapper modelMapper;

    //create form
    @PostMapping
    public SchoolDTO createForm (@RequestBody SchoolDTO schoolDto){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        String formattedDate = dateFormat.format(new Date());
        schoolDto.setCreatedDate(formattedDate);
        schoolDto.setStatus("Pending");
        schoolDto.setStatusMessage("Application is submitted by user");
        return schoolRepo.save(schoolDto);
    }

    //fetch all the details
    @GetMapping("/fetchAll")
    public List<SchoolDTO> fetchAllDetails(){
        return schoolRepo.findAll();
    }

    //fetch by name
    @GetMapping("/name/{name}")
    public ResponseEntity <List<SchoolDTO>> fetchDetailsByName(@PathVariable String name){
        List <SchoolDTO> schoolDTO = schoolRepo.findByName(name);
        if(schoolDTO.isEmpty()){
            throw new SchoolException(name + "not found");
        }
        return ResponseEntity.ok(schoolDTO);
    }

    //fetch by phone Number
    @GetMapping("/phone/{phone}")
    public ResponseEntity<SchoolDTO> fetchDetailsByPhone (@PathVariable Long phone){
        SchoolDTO schoolDTO = schoolRepo.findByPhone(phone).orElseThrow(()->new SchoolException(phone+" not found"));
        return ResponseEntity.ok(schoolDTO);
    }

    //fetch by percentage
    @GetMapping("/percent/{percentage}")
    public ResponseEntity<SchoolDTO> fetchDetailsByPercent (@PathVariable Double percentage){
        SchoolDTO schoolDTO = schoolRepo.findByPercentage(percentage).orElseThrow(()->new SchoolException(percentage+" not found"));
        return ResponseEntity.ok(schoolDTO);
    }

    //update details by phone
    @PutMapping("/update/{phone}/{updateMessgage}")
    public ResponseEntity<SchoolDTO> updateDetailsByPhone (@PathVariable Long phone, @PathVariable String updateMessgage, @RequestBody SchoolDTO studentDetails){
        SchoolDTO schoolDTO = schoolRepo.findByPhone(phone).orElseThrow(() -> new SchoolException(phone + " not found"));
        String existingCreatedDate = schoolDTO.getCreatedDate();
        String existingStatus = schoolDTO.getStatus();
        int existingUpdateCount = schoolDTO.getUpdateCount();
        if (existingUpdateCount ==5){ throw new SchoolException(" You can't update more than 5 times "); }
        modelMapper.map(studentDetails, schoolDTO);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        String formattedDate = dateFormat.format(new Date());
        schoolDTO.setUpdateCount(existingUpdateCount + 1);
        schoolDTO.setCreatedDate(existingCreatedDate);
        schoolDTO.setStatus(existingStatus);
        schoolDTO.setUpdatedDate(formattedDate);
        schoolDTO.setUpdateMessage(updateMessgage);
        schoolRepo.save(schoolDTO);
        return ResponseEntity.ok(schoolDTO);
    }

    //delete form by phone
    @DeleteMapping("/delete/{phone}")
    public ResponseEntity<SchoolDTO> deleteEmployee(@PathVariable Long phone) {
        SchoolDTO schoolDTO = schoolRepo.findByPhone(phone).orElseThrow(()-> new SchoolException(phone + "not found"));
        schoolRepo.delete(schoolDTO);
        return ResponseEntity.noContent().build();
    }
}
