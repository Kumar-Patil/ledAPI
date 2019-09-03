package com.ledVan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.ledVan.exception.ResourceNotFoundException;
import com.ledVan.model.District;
import com.ledVan.repository.DistrictRepository;
import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "District API List", description = "District API List")
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepository;

    /**
     *
     * @return
     */
    @ApiOperation(value = "View a list of available district", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/district")
    public List<District> getAll() {
        return (List<District>) districtRepository.findAll();
    }

    @ApiOperation(value = "Get district by Id")
    @GetMapping("/district/{id}")
    public ResponseEntity<District> getById(
            @ApiParam(value = "district id from which district object will retrieve", required = true)
            @PathVariable(value = "id") Long districtId)
            throws ResourceNotFoundException {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("district not found for this id :: " + districtId));
        return ResponseEntity.ok().body(district);
    }

    @ApiOperation(value = "Add district")
    @PostMapping("/district")
    public District create(
            @ApiParam(value = "district object store in database table", required = true)
            @Valid @RequestBody District district) {
        district.setCreatedAt(new Date());
        district.setUpdatedAt(new Date());
        return districtRepository.save(district);
    }

    @ApiOperation(value = "Update district")
    @PutMapping("/district/{id}")
    public ResponseEntity<District> update(
            @ApiParam(value = "District Id to update district object", required = true)
            @PathVariable(value = "id") Long districtId,
            @ApiParam(value = "Update district object", required = true)
            @Valid @RequestBody District districtDetails) throws ResourceNotFoundException {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District not found for this id :: " + districtId));
        district.setDistrictName(districtDetails.getDistrictName());
        district.setUpdatedAt(new Date());
        final District updatedDistrict = districtRepository.save(district);
        return ResponseEntity.ok(updatedDistrict);
    }

    @ApiOperation(value = "Delete district")
    @DeleteMapping("/district/{id}")
    public Map<String, Boolean> delete(
            @ApiParam(value = "District Id from which district object will delete from database table", required = true)
            @PathVariable(value = "id") Long districtId)
            throws ResourceNotFoundException {
        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District not found for this id :: " + districtId));
        districtRepository.delete(district);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
