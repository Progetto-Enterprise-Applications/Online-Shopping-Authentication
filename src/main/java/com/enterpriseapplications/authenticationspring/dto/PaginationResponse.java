package com.enterpriseapplications.authenticationspring.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> results;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalElements;
}
