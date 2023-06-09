package com.example.rentacar.business.dto.responses.get;

import com.example.rentacar.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBrandsResponse {
    private int id;
    private String name;
//    private List<GetAllModelsResponse> getAllModelsResponses;
}
