package com.institute.school.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "admin")
public class AdminDTO {
    @Id
    @GeneratedValue
    @Column (name = "s.no")
    private Long id;
    private String userName;
    private Long userPhone;
    private String status;
    private String statusMessage;
    @OneToOne
    @JoinColumn(name = "phone")
    private SchoolDTO schoolDetails;
}
