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
import com.ledVan.model.PanelUser;
import com.ledVan.repository.PanelUserRepository;
import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "PanelUser API List", description = "PanelUser API List")
public class PanelUserController {

    @Autowired
    private PanelUserRepository panelUserRepository;

    /**
     *
     * @return
     */
    @ApiOperation(value = "View a list of available paneluser", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/paneluser")
    public List<PanelUser> getAll() {
        return (List<PanelUser>) panelUserRepository.findAll();
    }

    @ApiOperation(value = "Get paneluser by Id")
    @GetMapping("/paneluser/{id}")
    public ResponseEntity<PanelUser> getById(
            @ApiParam(value = "paneluser id from which paneluser object will retrieve", required = true)
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        PanelUser paneluser = panelUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("paneluser not found for this id :: " + id));
        return ResponseEntity.ok().body(paneluser);
    }

    @ApiOperation(value = "Add paneluser")
    @PostMapping("/paneluser")
    public PanelUser create(
            @ApiParam(value = "paneluser object store in database table", required = true)
            @Valid @RequestBody PanelUser panelUser) {
        panelUser.setCreatedAt(new Date());
        panelUser.setUpdatedAt(new Date());
        return panelUserRepository.save(panelUser);
    }

    @ApiOperation(value = "update paneluser")
    @PutMapping("/paneluser/{id}")
    public ResponseEntity<PanelUser> update(
            @ApiParam(value = "paneluser Id to update paneluser object", required = true)
            @PathVariable(value = "id") Long districtId,
            @ApiParam(value = "Update paneluser object", required = true)
            @Valid @RequestBody PanelUser panelUser) throws ResourceNotFoundException {
        PanelUser panelUser1 = panelUserRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("paneluser not found for this id :: " + districtId));

        panelUser1.setDistrictName(panelUser.getDistrictName());
        panelUser1.setDistrictId(panelUser.getDistrictId());
        panelUser1.setEmail(panelUser.getEmail());
        panelUser1.setMobileNumber(panelUser.getMobileNumber());
        panelUser1.setPassword(panelUser.getPassword());
        panelUser1.setUpdatedAt(new Date());

        final PanelUser updatedPanelUser = panelUserRepository.save(panelUser1);
        return ResponseEntity.ok(updatedPanelUser);
    }

    @ApiOperation(value = "delete paneluser")
    @DeleteMapping("/paneluser/{id}")
    public Map<String, Boolean> delete(
            @ApiParam(value = "paneluser Id from which paneluser object will delete from database table", required = true)
            @PathVariable(value = "id") Long districtId)
            throws ResourceNotFoundException {
        PanelUser district = panelUserRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("paneluser not found for this id :: " + districtId));
        panelUserRepository.delete(district);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
