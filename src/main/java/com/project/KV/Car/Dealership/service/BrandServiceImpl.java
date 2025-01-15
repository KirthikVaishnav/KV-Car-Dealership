package com.project.KV.Car.Dealership.service;

import com.project.KV.Car.Dealership.exceptions.APIException;
import com.project.KV.Car.Dealership.exceptions.ResourceNotFoundException;
import com.project.KV.Car.Dealership.model.Brand;
import com.project.KV.Car.Dealership.payload.BrandDTO;
import com.project.KV.Car.Dealership.payload.BrandResponse;
import com.project.KV.Car.Dealership.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Long nextId = 1L;

    @Override
    public BrandResponse getAllBrands(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Brand> brandPage = brandRepository.findAll(pageDetails);

        List<Brand> brands = brandPage.getContent();

        if (brands.isEmpty()){
            throw new APIException("No Brand created till now");
        }

        List<BrandDTO> brandDTOS = brands.stream()
                .map(brand -> modelMapper.map(brand,BrandDTO.class))
                .toList();

        BrandResponse brandResponse=new BrandResponse();
        brandResponse.setContent(brandDTOS);
        brandResponse.setPageNumber(brandPage.getNumber());
        brandResponse.setPageSize(brandPage.getNumber());
        brandResponse.setTotalElements(brandPage.getTotalElements());
        brandResponse.setTotalPages(brandPage.getTotalPages());
        brandResponse.setLastPage(brandPage.isLast());
        return brandResponse;
    }

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
       Brand brand = modelMapper.map(brandDTO,Brand.class);
       Brand brandFromDb = brandRepository.findByBrandName(brand.getBrandName());
       if (brandFromDb != null)
           throw new APIException("Brand :"+brand.getBrandName()+" already exist");

       Brand savedBrand = brandRepository.save(brand);
       return modelMapper.map(savedBrand,BrandDTO.class);

    }

    @Override
    public BrandDTO deleteBrand(Long brandId) {
        Brand brand=brandRepository.findById(brandId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Brand","brandId",brandId)
                );

        brandRepository.delete(brand);
        return modelMapper.map(brand,BrandDTO.class);
    }

    @Override
    public BrandDTO updateBrand(BrandDTO brandDTO, Long brandId) {


        Brand savedBrand = brandRepository.findById(brandId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Brand","brandId",brandId)
                );
        Brand brand =modelMapper.map(brandDTO,Brand.class);
        brand.setBrandId(brandId);
        savedBrand=brandRepository.save(brand);
        return modelMapper.map(savedBrand,BrandDTO.class);
    }
}
