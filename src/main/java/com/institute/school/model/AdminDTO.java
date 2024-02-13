package com.institute.school.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "admin")
public class AdminDTO {
    private String name;
    @Id
    private Long phone;
    private String status;
    private String statusMessage;
    @OneToOne
    @JoinColumn(name = "phone")
    private SchoolDTO schoolDetails;
}
