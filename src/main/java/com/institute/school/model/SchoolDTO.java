package com.institute.school.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "user")
public class SchoolDTO {
    private String name;
    private int age;
    @Id
    private Long phone;
    private String address;
    private String email;
    private String education;
    private String school;
    private String parentsName;
    private double percentage;
    private String createdDate;
    private String updatedDate;
    private String status;
    private String statusMessage;
    private String updateMessage;
    private int updateCount;
    @OneToOne(mappedBy = "schoolDetails", cascade = CascadeType.MERGE)
    private AdminDTO statusDetails;

    public SchoolDTO(){
        this.updateCount = 0;
    }
}
