package com.project.KV.Car.Dealership.payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
     List<BrandDTO> content;
     Integer pageNumber;
     Integer pageSize;
     Long totalElements;
     Integer totalPages;
     boolean lastPage;
}
