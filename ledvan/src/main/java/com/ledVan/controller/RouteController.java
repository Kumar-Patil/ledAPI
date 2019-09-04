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
import com.ledVan.model.RouteMap;
import com.ledVan.repository.RouteMapRepository;
import java.util.Date;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "RouteMap API List", description = "RouteMap API List")
public class RouteController {

    @Autowired
    private RouteMapRepository routeMapRepository;

    /**
     *
     * @return
     */
    @ApiOperation(value = "View a list of available RouteMap", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/routemap")
    public List<RouteMap> getAll() {
        return (List<RouteMap>) routeMapRepository.findAll();
    }

    @ApiOperation(value = "Get routemap by Id")
    @GetMapping("/routemap/{id}")
    public ResponseEntity<RouteMap> getById(
            @ApiParam(value = "RouteMap id from which routeMap object will retrieve", required = true)
            @PathVariable(value = "id") Long districtId)
            throws ResourceNotFoundException {
        RouteMap district = routeMapRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("district not found for this id :: " + districtId));
        return ResponseEntity.ok().body(district);
    }

    @ApiOperation(value = "Add routemap")
    @PostMapping("/routemap")
    public RouteMap create(
            @ApiParam(value = "routemap object store in database table", required = true)
            @Valid @RequestBody RouteMap routeMap) {
        routeMap.setCreatedAt(new Date());
        routeMap.setUpdatedAt(new Date());
        return routeMapRepository.save(routeMap);
    }

    @ApiOperation(value = "Update routemap")
    @PutMapping("/routemap/{id}")
    public ResponseEntity<RouteMap> update(
            @ApiParam(value = "routemap Id to update routemap object", required = true)
            @PathVariable(value = "id") Long id,
            @ApiParam(value = "Update routemap object", required = true)
            @Valid @RequestBody RouteMap routeMapDetails) throws ResourceNotFoundException {
        RouteMap routeMap = routeMapRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RouteMap not found for this id :: " + id));
        routeMap.setDistrictName(routeMapDetails.getDistrictName());
        routeMap.setDistrictId(routeMapDetails.getDistrictId());
        routeMap.setRouteMapName(routeMapDetails.getRouteMapName());
        routeMap.setUpdatedAt(new Date());
        final RouteMap updatedRouteMap = routeMapRepository.save(routeMap);
        return ResponseEntity.ok(updatedRouteMap);
    }

    @ApiOperation(value = "Delete routemap")
    @DeleteMapping("/routemap/{id}")
    public Map<String, Boolean> delete(
            @ApiParam(value = "RouteMap Id from which RouteMap object will delete from database table", required = true)
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        RouteMap routeMap = routeMapRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RouteMap not found for this id :: " + id));
        routeMapRepository.delete(routeMap);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
