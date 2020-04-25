package com.ledVan.controller;

import com.ledVan.exception.ResourceNotFoundException;
import com.ledVan.model.Admin;
import com.ledVan.repository.AdminRepository;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.ledVan.repository.AreaRepository;
import com.ledVan.repository.DistrictRepository;
import com.ledVan.repository.LedDisplayRepository;
import com.ledVan.repository.PanelUserRepository;
import com.ledVan.repository.VechicleDetailsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "Dashboard API List", description = "Area API List")
public class DashboardController {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private PanelUserRepository panelUserRepository;

    @Autowired
    private VechicleDetailsRepository vechicleDetailsRepository;

    @Autowired
    private LedDisplayRepository ledDisplayRepository;

    private static final String EXTERNAL_FILE_PATH = "C:\\Patil\\AWARD\\";

    @Autowired
    private AdminRepository adminRepository;
    
    /**
     *
     * @return
     */
    @ApiOperation(value = "Get available area count", response = Map.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/dashboard/areacount")
    public Map<String, Long> getAreaCount(@RequestHeader("loggedInUserId") Integer loggedInUserId) throws ResourceNotFoundException {
        System.out.println("loggedInUserId {}" + loggedInUserId);
        Admin admin = this.getUser(loggedInUserId);
        Map<String, Long> response = new HashMap<>();
        if (admin.getRoleId() == 3) {
            response.put("count", areaRepository.areaCount(admin.getDistrictId()));
        } else {
            response.put("count", areaRepository.areaCount());
        }
        return response;
    }

    /**
     *
     * @param loggedInUserId
     * @return
     */
    @ApiOperation(value = "Get available districtcount", response = Map.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/dashboard/districtcount")
    public Map<String, Long> getDistrictCount(@RequestHeader("loggedInUserId") Integer loggedInUserId) throws ResourceNotFoundException {
        Admin admin = this.getUser(loggedInUserId);
        Map<String, Long> response = new HashMap<>();
        if (admin.getRoleId() == 3) {
            response.put("count", districtRepository.districtCount(admin.getDistrictId()));
        }else{
        response.put("count", districtRepository.districtCount());
        }
        return response;
    }

    /**
     *
     * @param loggedInUserId
     * @return
     */
    @ApiOperation(value = "Get available panelusercount", response = Map.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/dashboard/panelusercount")
    public Map<String, Long> getPanelUserCount(@RequestHeader("loggedInUserId") Integer loggedInUserId) throws ResourceNotFoundException {
        Map<String, Long> response = new HashMap<>();
        Admin admin = this.getUser(loggedInUserId);
        if (admin.getRoleId() == 3) {
            response.put("count", panelUserRepository.panelUserCount(admin.getDistrictId()));
        }else{
        response.put("count", panelUserRepository.panelUserCount());
        }
        return response;
    }

    /**
     *
     * @param loggedInUserId
     * @return
     */
    @ApiOperation(value = "Get available vechiclecount", response = Map.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping("/dashboard/vechiclecount")
    public Map<String, Long> getVechicalCount(@RequestHeader("loggedInUserId") Integer loggedInUserId) throws ResourceNotFoundException {
        Map<String, Long> response = new HashMap<>();
        Admin admin = this.getUser(loggedInUserId);
        if (admin.getRoleId() == 3) {
            response.put("count", vechicleDetailsRepository.vechicleCount(admin.getDistrictId()));
        } else {
            response.put("count", vechicleDetailsRepository.vechicleCount());
        }
        return response;
    }

    @ApiOperation(value = "Get report count")
    @GetMapping("/dashboard/reportcount/unreviedreport")
    public Map<String, Long> getUnreviedreport(@RequestHeader("loggedInUserId") Integer loggedInUserId)
            throws ResourceNotFoundException {
        Map<String, Long> response = new HashMap<>();
        Admin admin = this.getUser(loggedInUserId);
        if (admin.getRoleId() == 3) {
            response.put("count", ledDisplayRepository.unreviewedReport("New", admin.getDistrictId()));
        } else {
            response.put("count", ledDisplayRepository.unreviewedReport("New"));
        }
        return response;
    }
    
    @ApiOperation(value = "Get report count")
    @GetMapping("/dashboard/reportcount/revieddreport")
    public Map<String, Long> getRevieddreport(@RequestHeader("loggedInUserId") Integer loggedInUserId)
            throws ResourceNotFoundException {
        Map<String, Long> response = new HashMap<>();
        Admin admin = this.getUser(loggedInUserId);
        if (admin.getRoleId() == 3) {
            response.put("count", ledDisplayRepository.reviewedReport("New"));
        }else{
        response.put("count", ledDisplayRepository.reviewedReport("New"));
        }
        return response;
    }
    
    private Admin getUser(long loggedInUserId) throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(loggedInUserId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + loggedInUserId));
        return admin;
    }
}
