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
import com.ledVan.model.VehicleDetails;
import com.ledVan.repository.VechicleDetailsRepository;
import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "Vechicle details API List", description = "Vechicle details API List")
public class VechicleDetailsController {

    @Autowired
    private VechicleDetailsRepository vechicleDetailsRepository;

    /**
     *
     * @return
     */
    @ApiOperation(value = "View a list of available vehicleDetails", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/vehicleDetails")
    public List<VehicleDetails> getAll() {
        return vechicleDetailsRepository.findAll();
    }

    @ApiOperation(value = "Get vehicleDetails by Id")
    @GetMapping("/vehicleDetails/{id}")
    public ResponseEntity<VehicleDetails> getById(
            @ApiParam(value = "vehicleDetails id from which vehicleDetails object will retrieve", required = true)
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        VehicleDetails area = vechicleDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("vehicleDetails not found for this id :: " + id));
        return ResponseEntity.ok().body(area);
    }

    @ApiOperation(value = "Add vehicleDetails")
    @PostMapping("/vehicleDetails")
    public VehicleDetails create(
            @ApiParam(value = "vehicleDetails object store in database table", required = true)
            @Valid @RequestBody VehicleDetails vehicleDetails) {
        vehicleDetails.setCreatedAt(new Date());
        vehicleDetails.setUpdatedAt(new Date());
        return vechicleDetailsRepository.save(vehicleDetails);
    }

    @ApiOperation(value = "Update vehicleDetails")
    @PutMapping("/vehicleDetails/{id}")
    public ResponseEntity<VehicleDetails> update(
            @ApiParam(value = "area Id to update area object", required = true)
            @PathVariable(value = "id") Long id,
            @ApiParam(value = "Update VehicleDetails object", required = true)
            @Valid @RequestBody VehicleDetails vehicleDetails) throws ResourceNotFoundException {
        VehicleDetails details = vechicleDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("area not found for this id :: " + id));
        
        details.setDistrictAreaId(vehicleDetails.getDistrictAreaId());
        details.setDistrictAreaName(vehicleDetails.getDistrictAreaName());
        details.setDriverMobileNo(vehicleDetails.getDriverMobileNo());
        details.setDriverName(vehicleDetails.getDriverName());
        details.setDriverPicture(vehicleDetails.getDriverPicture());

        details.setOperatorMobileNo(vehicleDetails.getOperatorMobileNo());
        details.setOperatorName(vehicleDetails.getOperatorName());
        details.setOperatorPicture(vehicleDetails.getOperatorPicture());

        details.setVehicleDetails(vehicleDetails.getVehicleDetails());
        details.setVehicleNo(vehicleDetails.getVehicleNo());
        details.setUpdatedAt(new Date());
        final VehicleDetails updatedVehicleDetails = vechicleDetailsRepository.save(details);
        return ResponseEntity.ok(updatedVehicleDetails);
    }

    @ApiOperation(value = "Delete vehicleDetails")
    @DeleteMapping("/vehicleDetails/{id}")
    public Map<String, Boolean> delete(
            @ApiParam(value = "area Id from which vehicleDetails object will delete from database table", required = true)
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        VehicleDetails vehicleDetails = vechicleDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("area not found for this id :: " + id));
        vechicleDetailsRepository.delete(vehicleDetails);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
