package com.project.KV.Car.Dealership.repository;

import com.project.KV.Car.Dealership.model.Brand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    Brand findByBrandName(@NotBlank @Size(min = 2,message = "Brand Name must contain atleast 2 characters") String brandName);
}
