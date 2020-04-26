package com.ledVan.controller;

import com.ledVan.RequestMapper.Login;
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
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@Api(value = "admin API List", description = "admin API List")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	/**
	 *
	 * @param loggedInUserId
	 * @return
	 */
	@ApiOperation(value = "View a list of available admins", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/admin")
	public List<Admin> getAll() {
		return (List<Admin>) adminRepository.findAll();
	}

	@ApiOperation(value = "Get admin by Id")
	@GetMapping("/admin/{id}")
	public ResponseEntity<Admin> getById(
			@ApiParam(value = "admin id from which admin object will retrieve", required = true) @PathVariable(value = "id") Long districtId)
			throws ResourceNotFoundException {
		Admin admin = adminRepository.findById(districtId)
				.orElseThrow(() -> new ResourceNotFoundException("admin not found for this id :: " + districtId));
		return ResponseEntity.ok().body(admin);
	}

	@ApiOperation(value = "Add admin")
	@PostMapping("/admin")
	public Admin create(
			@ApiParam(value = "admin object store in database table", required = true) @Valid @RequestBody Admin admin)
			throws EncryptionDecryptionException {
		admin.setCreatedAt(new Date());
		admin.setUpdatedAt(new Date());
		admin.setPassword(EncryptionDecryption.encrypt(admin.getPassword(), Constants.SECRETKEY));
		return adminRepository.save(admin);
	}

	@ApiOperation(value = "Update admin")
	@PutMapping("/admin/{id}")
	public ResponseEntity<Admin> update(
			@ApiParam(value = "admin Id to update admin object", required = true) @PathVariable(value = "id") Long id,
			@ApiParam(value = "Update admin object", required = true) @Valid @RequestBody Admin adminDetails)
			throws ResourceNotFoundException, EncryptionDecryptionException {
		Admin admin = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("District not found for this id :: " + id));

		admin.setUpdatedAt(new Date());
		admin.setEmail(adminDetails.getEmail());
		admin.setFirstName(adminDetails.getFirstName());
		admin.setLastName(adminDetails.getLastName());
		admin.setMobileNo(adminDetails.getMobileNo());
		admin.setPassword(EncryptionDecryption.encrypt(admin.getPassword(), Constants.SECRETKEY));
		admin.setDistrictId(adminDetails.getDistrictId());
		admin.setDistrictName(adminDetails.getDistrictName());
		admin.setId(adminDetails.getRoleId());
		admin.setRoleName(Constants.roleName(adminDetails.getRoleId()));
		final Admin adminDetails1 = adminRepository.save(admin);
		return ResponseEntity.ok(adminDetails1);
	}

	@ApiOperation(value = "Delete admin")
	@DeleteMapping("/admin/{id}")
	public Map<String, Boolean> delete(
			@ApiParam(value = "admin Id from which admin object will delete from database table", required = true) @PathVariable(value = "id") Long districtId)
			throws ResourceNotFoundException {
		Admin admin = adminRepository.findById(districtId)
				.orElseThrow(() -> new ResourceNotFoundException("admin not found for this id :: " + districtId));
		adminRepository.delete(admin);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@ApiOperation(value = "Admin Login")
	@PostMapping("/admin/login")
	public ResponseEntity<Admin> login(
			@ApiParam(value = "Admin Login", required = true) @Valid @RequestBody Login login)
			throws EncryptionDecryptionException {
		Admin admin = adminRepository.getUser(login.getUserName(),
				EncryptionDecryption.encrypt(login.getPassword(), Constants.SECRETKEY));
		if (admin == null) {
			ResponseEntity.ok(new ResourceNotFoundException("Admin not found for this id :: " + login.getUserName()));
		}
		return ResponseEntity.ok(admin);
	}

	@ApiOperation(value = "Change password")
	@PutMapping("/admin/changepassword/{id}")
	public ResponseEntity<Admin> changePassword(
			@ApiParam(value = "Change password", required = true) @PathVariable(value = "id") Long id,
			@ApiParam(value = "Change password", required = true) @RequestParam String password)
			throws ResourceNotFoundException, EncryptionDecryptionException {
		Admin admin = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found for this id :: " + id));
		admin.setPassword(EncryptionDecryption.encrypt(password, Constants.SECRETKEY));
		final Admin updatedAdmin = adminRepository.save(admin);
		return ResponseEntity.ok(updatedAdmin);
	}
}
