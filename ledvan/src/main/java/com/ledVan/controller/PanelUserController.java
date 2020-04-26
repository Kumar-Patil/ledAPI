package com.ledVan.controller;

import com.ledVan.Util.Constants;
import com.ledVan.Util.EncryptionDecryption;

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

import com.ledVan.exception.EncryptionDecryptionException;
import com.ledVan.exception.ResourceNotFoundException;
import com.ledVan.model.Admin;
import com.ledVan.repository.AdminRepository;
import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "PanelUser API List", description = "PanelUser API List")
public class PanelUserController {

    @Autowired
    private AdminRepository adminRepository;

    /**
     *
     * @return
     */
    @ApiOperation(value = "View a list of available panel", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/panel")
    public List<Admin> getAll() {
        return (List<Admin>) adminRepository.findAll();
    }

    @ApiOperation(value = "Get panel by Id")
    @GetMapping("/panel/{id}")
    public ResponseEntity<Admin> getById(
            @ApiParam(value = "panel id from which admin object will retrieve", required = true)
            @PathVariable(value = "id") Long districtId)
            throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("panel not found for this id :: " + districtId));
        return ResponseEntity.ok().body(admin);
    }

    @ApiOperation(value = "Add panel")
    @PostMapping("/panel")
    public Admin create(
            @ApiParam(value = "panel object store in database table", required = true)
            @Valid @RequestBody Admin admin) throws EncryptionDecryptionException {
        admin.setCreatedAt(new Date());
        admin.setUpdatedAt(new Date());
        admin.setRoleId(3);
        admin.setRoleName(Constants.roleName(3));
        admin.setPassword(EncryptionDecryption.encrypt(admin.getPassword(), Constants.SECRETKEY));
        return adminRepository.save(admin);
    }

    @ApiOperation(value = "Update panel")
    @PutMapping("/panel/{id}")
    public ResponseEntity<Admin> update(
            @ApiParam(value = "panel Id to update panel object", required = true)
            @PathVariable(value = "id") Long id,
            @ApiParam(value = "Update panel object", required = true)
            @Valid @RequestBody Admin adminDetails) throws ResourceNotFoundException, EncryptionDecryptionException {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("panel not found for this id :: " + id));

        admin.setUpdatedAt(new Date());
        admin.setEmail(adminDetails.getEmail());
        admin.setFirstName(adminDetails.getFirstName());
        admin.setLastName(adminDetails.getLastName());
        admin.setMobileNo(adminDetails.getMobileNo());
        admin.setPassword(EncryptionDecryption.encrypt(admin.getPassword(), Constants.SECRETKEY));
        admin.setDistrictId(adminDetails.getDistrictId());
        admin.setDistrictName(adminDetails.getDistrictName());
        admin.setRoleId(3);
        admin.setRoleName(Constants.roleName(3));
        final Admin adminDetails1 = adminRepository.save(admin);
        return ResponseEntity.ok(adminDetails1);
    }

    @ApiOperation(value = "Delete panel")
    @DeleteMapping("/panel/{id}")
    public Map<String, Boolean> delete(
            @ApiParam(value = "panel Id from which panel object will delete from database table", required = true)
            @PathVariable(value = "id") Long districtId)
            throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("panel not found for this id :: " + districtId));
        adminRepository.delete(admin);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
