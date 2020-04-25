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
import com.ledVan.model.Admin;
import com.ledVan.model.AreaDetails;
import com.ledVan.repository.AdminRepository;
import com.ledVan.repository.AreaRepository;
import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "Area API List", description = "Area API List")
public class AreaController {
    
    @Autowired
    private AreaRepository areaRepository;
    
    @Autowired
    private AdminRepository adminRepository;

    /**
     *
     * @param loggedInUserId
     * @return
     */
    @ApiOperation(value = "View a list of available area", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/area")
    public List<AreaDetails> getAll(@RequestHeader("loggedInUserId") Integer loggedInUserId) throws ResourceNotFoundException {
        Admin admin = this.getUser(loggedInUserId);
        if (admin.getRoleId() == 3) {
            return areaRepository.getListOfAreas(admin.getDistrictId());
        } else {
            return (List<AreaDetails>) areaRepository.findAll();
        }
        
    }
    
    @ApiOperation(value = "Get area by Id")
    @GetMapping("/area/{id}")
    public ResponseEntity<AreaDetails> getById(
            @ApiParam(value = "area id from which area object will retrieve", required = true)
            @PathVariable(value = "id") Long areaId)
            throws ResourceNotFoundException {
        AreaDetails area = areaRepository.findById(areaId)
                .orElseThrow(() -> new ResourceNotFoundException("area not found for this id :: " + areaId));
        return ResponseEntity.ok().body(area);
    }
    
    @ApiOperation(value = "Add area")
    @PostMapping("/area")
    public AreaDetails create(
            @ApiParam(value = "area object store in database table", required = true)
            @Valid @RequestBody AreaDetails area) {
        area.setCreatedAt(new Date());
        area.setUpdatedAt(new Date());
        return areaRepository.save(area);
    }
    
    @ApiOperation(value = "Update area")
    @PutMapping("/area/{id}")
    public ResponseEntity<AreaDetails> update(
            @ApiParam(value = "area Id to update area object", required = true)
            @PathVariable(value = "id") Long districtId,
            @ApiParam(value = "Update area object", required = true)
            @Valid @RequestBody AreaDetails areaDetails) throws ResourceNotFoundException {
        AreaDetails area = areaRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("area not found for this id :: " + districtId));
        area.setDistrictName(areaDetails.getDistrictName());
        area.setAreaName(areaDetails.getAreaName());
        area.setDistrictId(areaDetails.getDistrictId());
        area.setUpdatedAt(new Date());
        final AreaDetails updatedArea = areaRepository.save(areaDetails);
        return ResponseEntity.ok(updatedArea);
    }
    
    @ApiOperation(value = "Delete area")
    @DeleteMapping("/area/{id}")
    public Map<String, Boolean> delete(
            @ApiParam(value = "area Id from which area object will delete from database table", required = true)
            @PathVariable(value = "id") Long districtId)
            throws ResourceNotFoundException {
        AreaDetails area = areaRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("area not found for this id :: " + districtId));
        areaRepository.delete(area);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
     private Admin getUser(long loggedInUserId) throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(loggedInUserId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + loggedInUserId));
        return admin;
    }
}
