package com.project.KV.Car.Dealership.service;

import com.project.KV.Car.Dealership.model.Brand;
import com.project.KV.Car.Dealership.payload.BrandDTO;
import com.project.KV.Car.Dealership.payload.BrandResponse;

import java.util.List;

public interface BrandService {
    BrandResponse getAllBrands(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    BrandDTO createBrand(BrandDTO brandDTO);

    BrandDTO deleteBrand(Long brandId);

    BrandDTO updateBrand(BrandDTO brandDTO, Long brandId);
}
