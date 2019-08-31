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
import com.ledVan.model.LedDisplay;
import com.ledVan.repository.LedDisplayRepository;
import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "Led Display API List", description = "District API List")
public class LedDisplayController {

    @Autowired
    private LedDisplayRepository ledDisplayRepository;

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
    @GetMapping("/leddisplay")
    public List<LedDisplay> getAll() {
        return ledDisplayRepository.findAll();
    }

    @ApiOperation(value = "Get district by Id")
    @GetMapping("/leddisplay/{id}")
    public ResponseEntity<LedDisplay> getById(
            @ApiParam(value = "LedDisplay id from which LedDisplay object will retrieve", required = true)
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        LedDisplay district = ledDisplayRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LedDisplay not found for this id :: " + id));
        return ResponseEntity.ok().body(district);
    }

    @ApiOperation(value = "Add LedDisplay")
    @PostMapping("/leddisplay")
    public LedDisplay create(
            @ApiParam(value = "district object store in database table", required = true)
            @Valid @RequestBody LedDisplay ledDisplay) {
        ledDisplay.setCreatedAt(new Date());
        ledDisplay.setUpdatedAt(new Date());
        return ledDisplayRepository.save(ledDisplay);
    }

    @ApiOperation(value = "Update LedDisplay")
    @PutMapping("/leddisplay/{id}")
    public ResponseEntity<LedDisplay> update(
            @ApiParam(value = "LedDisplay Id to update LedDisplay object", required = true)
            @PathVariable(value = "id") Long districtId,
            @ApiParam(value = "Update district object", required = true)
            @Valid @RequestBody LedDisplay display) throws ResourceNotFoundException {
        LedDisplay ledDisplay = ledDisplayRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("ledDisplay not found for this id :: " + districtId));
        ledDisplay.setStatus(display.getStatus());
        ledDisplay.setVechicleNo(display.getVechicleNo());
        ledDisplay.setUpdatedAt(new Date());
        final LedDisplay updatedLedDisplay = ledDisplayRepository.save(ledDisplay);
        return ResponseEntity.ok(updatedLedDisplay);
    }

    @ApiOperation(value = "Delete LedDisplay")
    @DeleteMapping("/leddisplay/{id}")
    public Map<String, Boolean> delete(
            @ApiParam(value = "LedDisplay Id from which LedDisplay object will delete from database table", required = true)
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        LedDisplay ledDisplay = ledDisplayRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("District not found for this id :: " + id));
        ledDisplayRepository.delete(ledDisplay);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
