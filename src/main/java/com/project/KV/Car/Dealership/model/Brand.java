package com.project.KV.Car.Dealership.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "brands")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    @NotBlank
    @Size(min = 2,message = "Brand Name must contain atleast 2 characters")
    private String brandName;


}
