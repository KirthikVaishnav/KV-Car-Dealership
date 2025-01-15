package com.project.KV.Car.Dealership.controller;

import com.project.KV.Car.Dealership.config.AppConstants;
import com.project.KV.Car.Dealership.payload.BrandDTO;
import com.project.KV.Car.Dealership.payload.BrandResponse;
import com.project.KV.Car.Dealership.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/public/brands")
    public ResponseEntity<BrandResponse> getAllBrands(
            @RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_BRAND_BY,required = false)String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppConstants.SORT_DIR,required = false)String sortOrder){
        BrandResponse brandResponse = brandService.getAllBrands(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(brandResponse, HttpStatus.OK);
    }

    @PostMapping("/admin/brands")
    public ResponseEntity<BrandDTO> createBrand(@Valid @RequestBody BrandDTO brandDTO){
        BrandDTO savedbrandDTO = brandService.createBrand(brandDTO);
        return new ResponseEntity<>(savedbrandDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/brands/{brandId}")
    public ResponseEntity<BrandDTO> deleteBrand(@PathVariable Long brandId){
        BrandDTO brandDTO = brandService.deleteBrand(brandId);
        return new ResponseEntity<>(brandDTO,HttpStatus.CREATED);
    }

    @PutMapping("/admin/brands/{brandId}")
    public ResponseEntity<BrandDTO> updateBrand(@RequestBody BrandDTO brandDTO,@PathVariable Long brandId) {
            BrandDTO savedBrandDTO = brandService.updateBrand(brandDTO, brandId);
          return  new ResponseEntity<>(savedBrandDTO,HttpStatus.OK);
    }

}
