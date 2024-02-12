package com.institute.school.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "admin")
@Embeddable
public class AdminDTO {
    private String status;
    private String statusMessage;
}
